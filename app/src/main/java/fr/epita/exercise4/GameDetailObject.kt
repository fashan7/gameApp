package fr.epita.exercise4

import com.google.gson.annotations.SerializedName

data class GameDetailObject(
    @SerializedName("id")
    var id : Int?,
    @SerializedName("name")
    var name:String?,

    @SerializedName("type")
    var type:String?,

    @SerializedName("players")
    var players:Int?,

    @SerializedName("year")
    var year:Int?,

    @SerializedName("url")
    var url:String?,

    @SerializedName("picture")
    var picture:String?,

    @SerializedName("description_fr")
    var description_fr:String?,

    @SerializedName("description_en")
    var description_en:String?,
    )