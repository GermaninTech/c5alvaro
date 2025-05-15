package com.example.c5alvaro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c5alvaro.data.Resum
import com.example.c5alvaro.databinding.ItemResumBinding

class ResumAdapter(private val resumList: List<Resum>) :
    RecyclerView.Adapter<ResumAdapter.ResumVH>() {

    inner class ResumVH(private val binding: ItemResumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resum: Resum) {
            binding.tvMateria.text = resum.materia
            binding.tvFetes.text = "Fetes: ${resum.fetes}"
            binding.tvPendents.text = "Pendents: ${resum.pendents}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumVH {
        val binding = ItemResumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResumVH(binding)
    }

    override fun onBindViewHolder(holder: ResumVH, position: Int) {
        holder.bind(resumList[position])
    }

    override fun getItemCount() = resumList.size
}
