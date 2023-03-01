package com.example.api_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.example.api_retrofit.adapter.PokemonInfoAdapter
import com.example.api_retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val pokemonListFragment = PokemonListFragment()
    private val pokemonInfoFragment = PokemonInfoFragment()
    private val pokemonImageFragment = PokemonImageFragment()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonInfoAdapter: PokemonInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_holder, pokemonListFragment)
            .commit()

        pokemonInfoAdapter = PokemonInfoAdapter(supportFragmentManager)
        pokemonInfoAdapter.add(pokemonInfoFragment, "Pokemon Information")
        pokemonInfoAdapter.add(pokemonImageFragment, "Pokemon Image")
        binding.pokemonInfoViewpager.adapter = pokemonInfoAdapter
    }
}