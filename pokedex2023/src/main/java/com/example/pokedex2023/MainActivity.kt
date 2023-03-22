package com.example.pokedex2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.example.pokedex2023.databinding.ActivityMainBinding
import com.example.pokedex2023.R
import com.example.pokedex2023.adapter.PokemonInfoAdapter

class MainActivity : AppCompatActivity() {
    private val pokemonListFragment = PokemonListFragment()
    private val pokemonInfoFragment = PokemonInfoFragment()
    private val pokemonImageFragment = PokemonImageFragment()
    private val pokemonMovesFragment = PokemonMovesFragment()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonInfoAdapter: PokemonInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pokedex 2023"

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_holder, pokemonListFragment)
            .commit()

        pokemonInfoAdapter = PokemonInfoAdapter(supportFragmentManager)
        pokemonInfoAdapter.add(pokemonInfoFragment, "Pokemon Information")
        pokemonInfoAdapter.add(pokemonMovesFragment, "Pokemon Moves")
        pokemonInfoAdapter.add(pokemonImageFragment, "Pokemon Image")
        binding.pokemonInfoViewpager.adapter = pokemonInfoAdapter


    }
}