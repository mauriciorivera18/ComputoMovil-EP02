package com.amaurypm.videogames.view

import androidx.recyclerview.widget.RecyclerView
import com.amaurypm.videogames.data.remote.model.Game
import com.amaurypm.videogames.databinding.GameElementBinding
import com.bumptech.glide.Glide

class GameViewHolder(
    private val binding: GameElementBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game){
        binding.tvTitle.text = game.title

        Glide.with(binding.root.context)
            .load(game.thumbnail)
            .into(binding.ivThumbnail)
    }
}