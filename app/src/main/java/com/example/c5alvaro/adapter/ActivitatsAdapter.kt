package com.example.c5alvaro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c5alvaro.data.Activitats
import com.example.c5alvaro.databinding.ItemActivitatsBinding
import java.time.format.DateTimeFormatter

class ActivitatsAdapter(private val activitatsList: MutableList<Activitats>) :
    RecyclerView.Adapter<ActivitatsAdapter.ActivitatsVH>() {

    class ActivitatsVH(private val binding: ItemActivitatsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun emplenar(activitat: Activitats) {
            binding.tvTitol.text = activitat.titol
            binding.tvMateria.text = activitat.Materia

            // Formateamos la fecha LocalDate a String tipo dd/MM/yyyy
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            binding.tvAutor.text = activitat.Data.format(formatter)

            binding.tvDescripcio.text = activitat.Estat
        }

        companion object {
            fun crear(parent: ViewGroup): ActivitatsVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemActivitatsBinding.inflate(layoutInflater, parent, false)
                return ActivitatsVH(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitatsVH {
        return ActivitatsVH.crear(parent)
    }

    override fun onBindViewHolder(holder: ActivitatsVH, position: Int) {
        holder.emplenar(activitatsList[position])
    }

    override fun getItemCount(): Int = activitatsList.size
}
