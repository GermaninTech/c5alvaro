package com.example.c5alvaro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.c5alvaro.adapter.ResumAdapter
import com.example.c5alvaro.data.Resum
import com.example.c5alvaro.databinding.FragmentResumBinding

class ResumFragment : Fragment() {

    private var _binding: FragmentResumBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ResumAdapter
    private val resumList = listOf(
        Resum("Matemàtiques", 5, 2),
        Resum("Física", 3, 4),
        Resum("Català", 7, 0),
        Resum("Anglès", 6, 1)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ResumAdapter(resumList)
        binding.rvResum.layoutManager = LinearLayoutManager(requireContext())
        binding.rvResum.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
