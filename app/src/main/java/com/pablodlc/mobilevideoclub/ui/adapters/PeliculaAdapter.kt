package com.pablodlc.mobilevideoclub.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.pablodlc.mobilevideoclub.R
import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula

class PeliculaAdapter(private var listaPeliculas: List<Pelicula>,
                      private val onRemoveClick: (Int) -> Unit,
                      private val onEditClick: (Pelicula) -> Unit) : RecyclerView.Adapter<PeliculaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.celda_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        holder.bind(listaPeliculas[position])
        val pelicula = listaPeliculas[position]
        holder.removeBtn.setOnClickListener {
            pelicula.id?.let { onRemoveClick(pelicula.id) }
        }

        holder.updateBtn.setOnClickListener {
            onEditClick(pelicula)
        }
    }

    fun actualizarListaPelicula(listaActualizada: List<Pelicula>) {
        listaPeliculas = listaActualizada
        bundleOf()
        notifyDataSetChanged()
    }
}