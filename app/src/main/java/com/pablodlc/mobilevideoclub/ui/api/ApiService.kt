package com.pablodlc.mobilevideoclub.ui.api

import com.pablodlc.mobilevideoclub.ui.dataclass.Pelicula
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/peliculas")
    fun getPeliculasApi(): Call<List<Pelicula>>

    @POST("api/peliculas")
    fun addPeliculaApi(@Body pelicula: Pelicula): Call<Pelicula>

    @DELETE("api/peliculas/{id}")
    fun removePeliculaApi(@Path("id") id: Int): Call<Void>

    @PUT("api/peliculas/{id}")
    fun updatePeliculaApi(@Path("id") id: Int, @Body pelicula: Pelicula): Call<Void>

}