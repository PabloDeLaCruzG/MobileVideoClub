package com.pablodlc.mobilevideoclub.ui.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pablodlc.mobilevideoclub.ui.api.ApiService
import com.pablodlc.mobilevideoclub.ui.api.RetrofitInstance
import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormViewModel : ViewModel() {

    fun addPelicula(pelicula: Pelicula) {

        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)
        apiService.addPeliculaApi(pelicula).enqueue(object : Callback<Pelicula> {
            override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                if (response.isSuccessful) {
                    Log.d("!!", "Pelicula creada")
                }
            }

            override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                Log.e("ERROR", "Error en FormViewModel")
            }

        })
    }

    fun updatePelicula(pelicula: Pelicula) {

        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val call = apiService.updatePeliculaApi(pelicula.id ?: -1, pelicula)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("!!", "Pelicula modificada")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("ERROR", "Error en FormViewModel")
            }

        })
    }
}