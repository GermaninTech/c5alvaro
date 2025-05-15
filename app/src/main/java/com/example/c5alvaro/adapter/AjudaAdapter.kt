package com.example.c5alvaro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c5alvaro.data.Ajuda
import com.example.c5alvaro.databinding.ItemAjudaBinding

class AjudaAdapter(private val llista: List<Ajuda>) :
    RecyclerView.Adapter<AjudaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAjudaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAjudaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = llista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ajuda = llista[position]
        holder.binding.tvTitol.text = ajuda.Titol
        holder.binding.tvDescripcio.text = ajuda.descripcio
    }
}