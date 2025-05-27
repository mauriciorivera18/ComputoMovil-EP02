package com.example.dbapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharaDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ki")
    val ki: String,
    @SerializedName("maxKi")
    val maxKi: String,
    @SerializedName("race")
    val race: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("affiliation")
    val aff: String,
    @SerializedName("transformations")
    val transformation: List<Transformation>
)

data class Transformation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ki")
    val ki: String,
    @SerializedName("image")
    val image: String
)
