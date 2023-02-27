package com.example.api_retrofit.models

import com.google.gson.annotations.SerializedName

class Pokemon {
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""

    constructor(name: String, url: String) {
        this.name = name
        this.url = url
    }
}

class PokemonListResponse {
    @SerializedName("count")
    var count: Int = -1

    @SerializedName("next")
    var next: String = ""

    @SerializedName("previous")
    var previous: String = ""

    @SerializedName("results")
    var pokemonList: ArrayList<Pokemon> = ArrayList<Pokemon>()
}



