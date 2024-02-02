package com.pablodlc.mobilevideoclub.ui.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pablodlc.mobilevideoclub.ui.api.ApiService
import com.pablodlc.mobilevideoclub.ui.api.RetrofitInstance
import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel(){

    private val _peliculasLiveData = MutableLiveData<List<Pelicula>?>()
    val peliculasLiveData: MutableLiveData<List<Pelicula>?> = _peliculasLiveData

    fun getPeliculas() {

        val service = RetrofitInstance.retrofit.create(ApiService::class.java)
        val call = service.getPeliculasApi()

        call.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {

                if(response.isSuccessful) {
                    _peliculasLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.e("ERROR", "Error en onResponse", t)

            }
        })
    }

    fun removePelicula(id: Int) {

        val service = RetrofitInstance.retrofit.create(ApiService::class.java)
        val call = service.removePeliculaApi(id)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("API", "Se ha eliminado correctamente")
                val listaActualizada = peliculasLiveData.value?.toMutableList()
                listaActualizada?.removeAll { it.id == id }
                _peliculasLiveData.value = listaActualizada
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("API ERROR", "Error al eliminar la pelicula: ", t)
            }

        })

    }

}