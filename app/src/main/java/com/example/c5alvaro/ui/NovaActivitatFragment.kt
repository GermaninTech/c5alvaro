package com.example.c5alvaro.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.c5alvaro.databinding.FragmentNovaActivitatBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class NovaActivitatFragment : Fragment() {

    private var _binding: FragmentNovaActivitatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNovaActivitatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Spinner con ejemplo de materias
        val materias = listOf("Matemàtiques", "Ciències", "Història", "Llengua")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, materias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SPMateria.adapter = adapter

        // Abrir DatePicker al hacer clic en ETData
        binding.ETData.setOnClickListener {
            showDatePicker()
        }

        binding.btnGuardar.setOnClickListener {
            val titol = binding.ETTitol.text?.toString()?.trim() ?: ""
            val materia = binding.SPMateria.selectedItem?.toString() ?: ""
            val data = binding.ETData.text?.toString()?.trim() ?: ""
            val feta = binding.CBEstat.isChecked
            val pendent = binding.CBEstat2.isChecked

            if (titol.isEmpty() || data.isEmpty()) {
                Toast.makeText(requireContext(), "Falten camps obligatoris!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (feta && pendent) {
                Toast.makeText(requireContext(), "Selecciona només un estat!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val activitat = hashMapOf(
                "titol" to titol,
                "materia" to materia,
                "data" to data,
                "feta" to feta,
                "pendent" to pendent
            )

            FirebaseFirestore.getInstance()
                .collection("activitats")
                .add(activitat)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Activitat guardada!", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Error al desar activitat!", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            val mesFormat = (selectedMonth + 1).toString().padStart(2, '0')
            val diaFormat = selectedDay.toString().padStart(2, '0')
            val fechaSeleccionada = "$selectedYear-$mesFormat-$diaFormat" // yyyy-MM-dd
            binding.ETData.setText(fechaSeleccionada)
        }, year, month, day)

        datePicker.show()
    }

    private fun clearFields() {
        binding.ETTitol.text?.clear()
        binding.ETData.text?.clear()
        binding.CBEstat.isChecked = false
        binding.CBEstat2.isChecked = false
        binding.SPMateria.setSelection(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
