package fr.epita.exercise4

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GameAdapter(val gameArray: List<GameObject>,
                  val context:Activity,
                  val itemClick:View.OnClickListener
                  )
    :RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var gamename : TextView
        val gameImg : ImageView
        init {
            gamename = itemView.findViewById(R.id.imgLbl)
            gameImg = itemView.findViewById(R.id.imgGm)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(context)
            .inflate(R.layout.card_layout, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.gamename.setOnClickListener(itemClick)
        return viewHolder
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        val game : GameObject = gameArray[position]
        holder.gamename.text = game.name
        Glide.with(context).load(game.picture).into(holder.gameImg)
        holder.gamename.tag = position
    }

    override fun getItemCount(): Int {
        return gameArray.size
    }

}