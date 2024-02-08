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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener los argumentos del Bundle
        val titulo = arguments?.getString("titulo")
        val descripcion = arguments?.getString("descripcion")
        val portada = arguments?.getString("portada")
        isEdit = arguments?.getBoolean("isEdit") ?: false

        // Asignar los valores a los campos del formulario
        binding.titleForm.setText(titulo)
        binding.descForm.setText(descripcion)
        binding.urlForm.setText(portada)

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)

        // Configurar el listener del botón de agregar
        binding.add.setOnClickListener {
            val tituloForm = binding.titleForm.text.toString()
            val descripcionForm = binding.descForm.text.toString()
            val portadaForm = binding.urlForm.text.toString()

            if (isEdit) {
                val id = arguments?.getInt("id")
                val peliculaEditada = Pelicula(id, tituloForm, descripcionForm, portadaForm)
                viewModel.updatePelicula(peliculaEditada)
            } else {
                val nuevaPelicula = Pelicula(null, tituloForm, descripcionForm, portadaForm)
                viewModel.addPelicula(nuevaPelicula)
            }

            // Limpiar los campos del formulario
            binding.titleForm.setText("")
            binding.descForm.setText("")
            binding.urlForm.setText("")
        }
    }


}