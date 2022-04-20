This is a basic Game App which displays a lists of small mini games. It is done using Retrofit webservice and Image render was used the Glide library.

<img width="351" alt="Screenshot 2022-04-20 at 11 58 58" src="https://user-images.githubusercontent.com/67037844/164203404-c47474c5-b9dc-4a6c-8cad-1930f5556c30.png">

This is achieved using retrofit. It was fetched from this https://education.3ie.fr/android/ressources/api/game/list.json

```kotlin:
@GET("android/ressources/api/game/list.json")
    fun getAllGames():Call<List<GameObject>>
```
This is an interface called ```interface WSGames```

App has a separate kotlin file to create a one time Retrofit Api Service which is a callable for any Acitivty
```kotlin:
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
```

To read the List of JSON's, it is mandatory to create an Object file to hold the items of the JSON response.
```kotlin:
data class GameObject(var id : Int, var name : String, var picture : String) 
```

Second Activity Layout is this

<img width="346" alt="Screenshot 2022-04-20 at 11 59 10" src="https://user-images.githubusercontent.com/67037844/164205609-5a7f6158-8f12-4053-a0b3-b0ff713bebd4.png">


<img width="359" alt="Screenshot 2022-04-20 at 11 59 29" src="https://user-images.githubusercontent.com/67037844/164205631-358eacb5-fbda-48c4-ae33-1e8e4508f7a5.png">

This is bit different, since the data is JSON task to do. When it comes to Dynamic, It is mandatory to pass parameter. 
```json:
{
	"id": 4,
	"name": "Battleship",
	"type": "strategy",
	"players": 2,
	"year": 1910,
	"url": "https://en.wikipedia.org/wiki/Battleship_(game)",
	"picture": "https://datagenetics.com/blog/december32011/progress.png",
	"description_fr": null,
	"description_en": "Battleship is played on ruled grids on which the players' fleets of ships are marked and concealed from the other player. Players alternate turns calling \"shots\" at the other player's ships in order to destroy the opposing player's fleet."
}
```

This Interface is acheived with the help of  ```Path```
```kotlin:
interface WSGameDetail {
    @GET("android/ressources/api/game/details{id}.json")
    fun getGame(@Path("id") gameid:Int): Call<GameDetailObject>
}
```

Object class can be created like this: to hold the Json Response
```kotlin:
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
   ```
   
   



