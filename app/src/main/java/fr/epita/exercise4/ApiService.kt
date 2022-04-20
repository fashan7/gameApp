package fr.epita.exercise4

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://education.3ie.fr/"
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

object API {
    val service : WSGames = retrofit.create(WSGames::class.java)
    val service_game : WSGameDetail = retrofit.create(WSGameDetail::class.java)
}