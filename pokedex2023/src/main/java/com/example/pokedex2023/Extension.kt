package com.example.pokedex2023

fun String.getPokemonID() : Int{
    var value = this.toString()
    val id = value.substring(34, value.length-1)
    return id.toInt()
}