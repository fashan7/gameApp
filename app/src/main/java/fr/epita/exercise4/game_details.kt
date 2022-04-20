package fr.epita.exercise4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Math.log

class game_details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        val orginIntent = intent
        val get_id = orginIntent.getStringExtra("id")?.toInt()
        if (get_id != null) {
            getGameData(get_id)
        }
    }

    fun getGameData(id:Int){
        API.service_game.getGame(id).enqueue(object : Callback<GameDetailObject>{
            override fun onResponse(
                call: Call<GameDetailObject>,
                response: Response<GameDetailObject>
            ) {
                if (response.isSuccessful) {
                    var details = response.body()!!
                    val gamename : TextView = findViewById(R.id.namevalue)
                    gamename.text = details.name
                    val type : TextView = findViewById(R.id.typevalue)
                    type.text = details.type
                    val player : TextView = findViewById(R.id.plyrvalue)
                    player.text = details.players.toString()
                    val year : TextView = findViewById(R.id.yearvalue)
                    year.text = details.year.toString()
                    val description : TextView = findViewById(R.id.description)
                    description.text = details.description_en
                    val imgGam : ImageView = findViewById(R.id.gameimgBg)
                    Glide.with(this@game_details).load(details.picture).into(imgGam)

                    val btnClck = findViewById<Button>(R.id.explore)
                    btnClck.setOnClickListener {
                        val intent  = Intent(android.content.Intent.ACTION_VIEW)
                        intent.data = Uri.parse(details.url)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<GameDetailObject>, t: Throwable) {
                Toast.makeText(this@game_details, "Error", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}