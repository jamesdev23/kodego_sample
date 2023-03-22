package com.example.pokedex2023.models

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

// added additional vars for pokemon info
class PokemonInfoResponse {
    @SerializedName("name")
    var name = ""

    @SerializedName("height")
    var height = -1

    @SerializedName("weight")
    var weight = -1

    @SerializedName("types")
    var types:ArrayList<PokemonTypeInfo> = ArrayList<PokemonTypeInfo>()

    @SerializedName("id")
    var id = -1

    @SerializedName("abilities")
    var abilities:ArrayList<PokemonAbilityInfo> = ArrayList<PokemonAbilityInfo>()

    @SerializedName("sprites")
    var sprites:PokemonSprite = PokemonSprite()

    @SerializedName("moves")
    var moves:ArrayList<PokemonMoveInfo> = ArrayList<PokemonMoveInfo>()
}

class PokemonAbility{
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""
}

class PokemonAbilityInfo{
    @SerializedName("ability")
    var ability:PokemonAbility = PokemonAbility()

    @SerializedName("is_hidden")
    var is_hidden = false

    @SerializedName("slot")
    var slot = -1
}

class PokemonSprite{
    @SerializedName("front_default")
    var front_default = ""

    @SerializedName("front_shiny")
    var front_shiny = ""
}


// added class for pokemon info
class PokemonType {
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""
}
class PokemonTypeInfo{
    @SerializedName("slot")
    var slot = -1

    @SerializedName("type")
    var type:PokemonType = PokemonType()
}
class PokemonMove {
    @SerializedName("name")
    var name = ""

    @SerializedName("url")
    var url = ""
}
class PokemonMoveInfo{
    @SerializedName("move")
    var move:PokemonMove = PokemonMove()
}


