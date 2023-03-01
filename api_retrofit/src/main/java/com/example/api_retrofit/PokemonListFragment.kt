package com.example.api_retrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_retrofit.adapter.PokemonAdapter
import com.example.api_retrofit.api.PokemonAPIClient
import com.example.api_retrofit.databinding.FragmentPokemonListBinding
import com.example.api_retrofit.models.Pokemon
import com.example.api_retrofit.models.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListFragment : Fragment() {

    var pokemonAdapter: PokemonAdapter? = null
    var pokemonList = ArrayList<Pokemon>()
    var binding:FragmentPokemonListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        var view = binding!!.root

        pokemonAdapter = PokemonAdapter(requireActivity().applicationContext, pokemonList)

        binding!!.pokemonList.layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)

        binding!!.pokemonList.adapter = pokemonAdapter

        getData()

        return view
    }

    private fun getData(){
        val call: Call<PokemonListResponse> =
            PokemonAPIClient.getPokemonData.getList(0, 100)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Log.d("API CALL", "Failed API CALL")
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                var response: PokemonListResponse = response!!.body()!!
                pokemonAdapter!!.setList(response.pokemonList)

                var pokelist = response.pokemonList
                for(pokemon in pokelist) {
                    Log.d("API CALL", pokemon.name)
                }
            }
        })
    }
}