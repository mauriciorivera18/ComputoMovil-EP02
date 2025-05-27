package com.example.dbapi.view

import androidx.recyclerview.widget.RecyclerView
import com.example.dbapi.data.remote.model.Items
import com.example.dbapi.databinding.GameElementBinding
import com.bumptech.glide.Glide

class CharaViewHolder(
    private val binding: GameElementBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(chara: Items){
        binding.tvName.text = chara.name
        binding.tvRating.text = chara.aff

        Glide.with(binding.root.context)
            .load(chara.image)
            .into(binding.ivThumbnail)
    }
}