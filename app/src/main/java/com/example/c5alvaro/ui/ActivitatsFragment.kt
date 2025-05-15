package com.example.c5alvaro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.c5alvaro.adapter.ActivitatsAdapter
import com.example.c5alvaro.data.Activitats
import com.example.c5alvaro.databinding.FragmentActivitatsBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ActivitatsFragment : Fragment() {

    private var _binding: FragmentActivitatsBinding? = null
    private val binding get() = _binding!!

    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: ActivitatsAdapter
    private val activitatsList = mutableListOf<Activitats>()
    private var listenerRegistration: ListenerRegistration? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ActivitatsAdapter(activitatsList)
        binding.RVactivitats.layoutManager = LinearLayoutManager(requireContext())
        binding.RVactivitats.adapter = adapter

        carregarActivitatsRealtime()
    }

    private fun carregarActivitatsRealtime() {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        listenerRegistration = db.collection("activitats")
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    Toast.makeText(requireContext(), "Error al carregar dades", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    activitatsList.clear()
                    for (doc in snapshots.documents) {
                        val titol = doc.getString("titol") ?: ""
                        val materia = doc.getString("materia") ?: ""
                        val dataStr = doc.getString("data") ?: ""

                        val data = try {
                            LocalDate.parse(dataStr, formatter)
                        } catch (e: Exception) {
                            LocalDate.now()
                        }

                        val feta = doc.getBoolean("feta") ?: false
                        val pendent = doc.getBoolean("pendent") ?: false

                        val estat = when {
                            feta -> "Feta"
                            pendent -> "Pendent"
                            else -> "Desconegut"
                        }

                        activitatsList.add(Activitats(titol, materia, data, estat))
                    }
                    adapter.notifyDataSetChanged()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listenerRegistration?.remove()  // Quitamos el listener para evitar leaks
        _binding = null
    }
}
