package fr.epita.exercise4

import retrofit2.Call
import retrofit2.http.GET

interface WSGames {
    @GET("android/ressources/api/game/list.json")
    fun getAllGames():Call<List<GameObject>>
}