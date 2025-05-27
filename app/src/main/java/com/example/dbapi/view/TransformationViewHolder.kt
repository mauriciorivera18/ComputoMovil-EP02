package com.example.dbapi.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dbapi.data.remote.model.Transformation
import com.example.dbapi.databinding.TransformationElementBinding

class TransformationViewHolder(
    private val binding: TransformationElementBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(chara: Transformation){
        binding.tvName.text = chara.name
        binding.tvRating.text = chara.ki

        Glide.with(binding.root.context)
            .load(chara.image)
            .into(binding.ivThumbnail)
    }
}