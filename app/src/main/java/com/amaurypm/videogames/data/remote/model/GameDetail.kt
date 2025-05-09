package com.amaurypm.videogames.data.remote.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("long_desc")
    val longDesc: String
)
