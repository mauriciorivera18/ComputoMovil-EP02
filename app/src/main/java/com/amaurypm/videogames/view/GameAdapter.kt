package com.amaurypm.videogames.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.videogames.data.remote.model.Game
import com.amaurypm.videogames.databinding.GameElementBinding

class GameAdapter(
    private val games: List<Game>
): RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int
    ) {

        val game = games[position]

        holder.bind(game)
    }

    override fun getItemCount(): Int = games.size
}