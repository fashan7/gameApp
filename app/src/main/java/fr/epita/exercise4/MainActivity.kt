package fr.epita.exercise4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var gamelist : RecyclerView
    private lateinit var manager : RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        getAllData()
    }

    fun getAllData(){
        API.service.getAllGames().enqueue(object : Callback<List<GameObject>>{
            override fun onResponse(
                call: Call<List<GameObject>>,
                response: Response<List<GameObject>>) {
                if (response.isSuccessful) {
                    var gameList = response.body()!!
                    val myclick = View.OnClickListener {
                        val postion = it.tag as Int
                        val clickGame = gameList[postion]
                        Intent(this@MainActivity, game_details::class.java).also { itInt->
                            itInt.putExtra("id", clickGame.id.toString())
                            startActivity(itInt)
                        }
                    }

                    gamelist = findViewById<RecyclerView>(R.id.main_cycler).apply {
                        layoutManager = manager
                        adapter = GameAdapter(gameList, this@MainActivity, myclick)
                        addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
                    }
                }
            }

            override fun onFailure(call: Call<List<GameObject>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}