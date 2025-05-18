package com.amaurypm.videogames.data.remote
import com.amaurypm.videogames.data.remote.model.PokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    //https://pokeapi.co/api/v2/pokemon/149/
    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String?
    ): PokemonDetail

}