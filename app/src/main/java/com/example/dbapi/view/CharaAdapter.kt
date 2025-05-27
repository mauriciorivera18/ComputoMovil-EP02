package com.example.dbapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbapi.data.remote.model.Items
import com.example.dbapi.databinding.GameElementBinding

class CharaAdapter(
    private val chara: List<Items>,
    private val onCharaClick: (Items) -> Unit
): RecyclerView.Adapter<CharaViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharaViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CharaViewHolder,
        position: Int
    ) {
        val character = chara[position]
        holder.bind(character)
        holder.itemView.setOnClickListener {
            onCharaClick(character)
        }
    }
    override fun getItemCount(): Int = chara.size
}