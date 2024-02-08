package com.pablodlc.mobilevideoclub.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pablodlc.mobilevideoclub.R
import com.pablodlc.mobilevideoclub.databinding.FragmentHomeBinding
import com.pablodlc.mobilevideoclub.ui.adapters.PeliculaAdapter
import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula
import com.pablodlc.mobilevideoclub.ui.models.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var peliculaAdapter: PeliculaAdapter
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        peliculaAdapter = PeliculaAdapter(ArrayList(), { id -> homeViewModel.removePelicula(id) }, { pelicula -> goToEdit(pelicula) })

        binding.recyclerView.adapter = peliculaAdapter

        homeViewModel.peliculasLiveData.observe(viewLifecycleOwner, Observer { peliculas -> if (peliculas != null) {peliculaAdapter.actualizarListaPelicula(peliculas)}  })

        homeViewModel.getPeliculas()

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_form)
        }

        return view

    }

    private fun goToEdit(pelicula: Pelicula) {
        val bundle = Bundle().apply {
            putInt("id", pelicula.id ?: -1)
            putString("titulo", pelicula.titulo)
            putString("descripcion", pelicula.descripcion)
            putString("portada", pelicula.portada)
            putBoolean("isEdit", true) // Indica que se está editando la película
        }
        findNavController().navigate(R.id.action_nav_home_to_nav_form, bundle)
    }
}