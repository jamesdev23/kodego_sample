package com.example.pokedex2021.api

import com.example.pokedex2021.models.PokemonInfoResponse
import com.example.pokedex2021.models.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon/")
    fun getList(
        @Query("offset") startIndex:Int,
        @Query("limit") limit:Int)
    : Call<PokemonListResponse>

    @GET("pokemon/{pokemonId}/")
    fun getPokemonInfo(
        @Path("pokemonId") pokemonID:Int)
            : Call<PokemonInfoResponse>
}