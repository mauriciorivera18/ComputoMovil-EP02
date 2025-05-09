package com.amaurypm.videogames.data.remote

import com.amaurypm.videogames.data.remote.model.Game
import com.amaurypm.videogames.data.remote.model.GameDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {
    //Aquí van los endpoints

    @GET("cm/games/games_list.php")
    suspend fun getGames(): List<Game>

    @GET("cm/games/game_detail.php")
    suspend fun getGameDetail(
        @Query("id") id: String?,
        @Query("name") name: String?
    ): GameDetail

    @GET("games/games_list")
    suspend fun getGamesApiary(): List<Game>

    @GET("games/game_detail/{id}")
    suspend fun getGameDetailApiary(
        @Path("id") id: String?
    ): GameDetail

}