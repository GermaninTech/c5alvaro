package com.example.c5alvaro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.c5alvaro.adapter.AjudaAdapter
import com.example.c5alvaro.data.Ajuda
import com.example.c5alvaro.databinding.FragmentAjudaBinding

class AjudaFragment : Fragment() {

    private var _binding: FragmentAjudaBinding? = null
    private val binding get() = _binding!!

    private val ajudaList = listOf(
        Ajuda("Nom de l'app", "EduTrack Andorra"),
        Ajuda("Versió", "1.0"),
        Ajuda("Desenvolupador", "Alvaro Garcia"),
        Ajuda("Descripció", "Aplicació per gestionar activitats escolars.")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAjudaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAjuda.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAjuda.adapter = AjudaAdapter(ajudaList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
