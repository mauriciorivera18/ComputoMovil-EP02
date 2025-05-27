package com.example.dbapi.data.remote

import com.example.dbapi.data.remote.model.Chara
import com.example.dbapi.data.remote.model.CharaDetail
import com.example.dbapi.data.remote.model.Items
import com.example.dbapi.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface CharaApi {
    @GET(Constants.CHARACTER_URL)
    suspend fun getChara(
    ):Chara

    @GET(Constants.CHARA_ID_URL)
    suspend fun getCharaDetail(
        @Path("id") id: String?
    ):CharaDetail
}