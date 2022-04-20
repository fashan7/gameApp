package fr.epita.exercise4

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WSGameDetail {
    @GET("android/ressources/api/game/details{id}.json")
    fun getGame(@Path("id") gameid:Int): Call<GameDetailObject>
}