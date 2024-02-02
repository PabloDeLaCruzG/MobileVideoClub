package com.pablodlc.mobilevideoclub.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pablodlc.mobilevideoclub.databinding.FragmentFormBinding
import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula
import com.pablodlc.mobilevideoclub.ui.models.FormViewModel

class FormFragment : Fragment() {

    private lateinit var viewModel: FormViewModel
    private lateinit var binding: FragmentFormBinding
    private var isEdit = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentFormBinding.inflate(inflater, container, false)

        val titulo = arguments?.getString("titulo")
        val descripcion = arguments?.getString("descripcion")
        val portada = arguments?.getString("portada")
        isEdit = arguments?.getBoolean("isEdit") ?: false

        binding.titleForm.setText(titulo);
        binding.descForm.setText(descripcion);
        binding.urlForm.setText(portada);

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        binding.add.setOnClickListener {
            val tituloForm = binding.titleForm.text
            val descripcionForm = binding.descForm.text
            val portadaForm = binding.urlForm.text

            val titulo : String = binding.titleForm.text.toString()
            val descripcion : String = binding.descForm.text.toString()
            val portada : String= binding.urlForm.text.toString()

            if (isEdit){
                var id = arguments?.getInt("id")
                val peliculaEditada = Pelicula(id, titulo, descripcion, portada)
                viewModel.updatePelicula(peliculaEditada)
            } else {
                val nuevaPelicula = Pelicula(null,titulo,descripcion,portada)
                viewModel.addPelicula(nuevaPelicula)
            }
            tituloForm.clear()
            descripcionForm.clear()
            portadaForm.clear()
        }
    }

}