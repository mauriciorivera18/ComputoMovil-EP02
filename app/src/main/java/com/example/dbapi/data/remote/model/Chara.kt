package com.example.dbapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class Chara(
    @SerializedName("items")
    val item: List<Items>
)

data class Items(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("affiliation")
    val aff: String
)