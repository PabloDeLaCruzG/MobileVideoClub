package com.pablodlc.mobilevideoclub.ui.adapters

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.pablodlc.mobilevideoclub.R
import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula
import com.squareup.picasso.Picasso

class PeliculaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val titulo = view.findViewById(R.id.tituloTextView) as TextView
    val descripcion = view.findViewById(R.id.descripcionTextView) as TextView
    val portada = view.findViewById(R.id.portadaImageView) as ImageView
    val removeBtn = view.findViewById(R.id.eliminarButton) as Button
    val updateBtn = view.findViewById(R.id.modificarButton) as Button

    fun bind(pelicula: Pelicula) {
        titulo.text = pelicula.titulo
        descripcion.text = pelicula.descripcion
        Picasso.get().load(pelicula.portada).into(portada)
    }
}