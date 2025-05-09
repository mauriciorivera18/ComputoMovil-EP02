package com.amaurypm.videogames.data.remote.model

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
)

