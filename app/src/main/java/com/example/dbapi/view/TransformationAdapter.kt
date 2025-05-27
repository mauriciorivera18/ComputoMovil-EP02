package com.example.dbapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbapi.data.remote.model.Transformation
import com.example.dbapi.databinding.TransformationElementBinding

class TransformationAdapter(
    private val chara: List<Transformation>,
    private val onCharaClick: (Transformation) -> Unit
): RecyclerView.Adapter<TransformationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransformationViewHolder {
        val binding = TransformationElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransformationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransformationViewHolder,
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