package com.example.pokedex2023

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex2023.adapter.PokemonAdapter
import com.example.pokedex2023.api.PokemonAPIClient
import com.example.pokedex2023.databinding.FragmentPokemonListBinding
import com.example.pokedex2023.models.Pokemon
import com.example.pokedex2023.models.PokemonListResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListFragment : Fragment() {

    var pokemonAdapter: PokemonAdapter? = null
    var pokemonList = ArrayList<Pokemon>()
    var binding:FragmentPokemonListBinding? = null
    private var startIndex: Int = 0
    private var limit: Int = 100
    private var offset: Int = 20
    private var maxPokemonCount = 1281

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        var view = binding!!.root

        pokemonAdapter = PokemonAdapter(requireActivity().applicationContext, pokemonList)

        binding!!.pokemonList.layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)

        binding!!.pokemonList.adapter = pokemonAdapter

        getData(startIndex, limit)

        binding!!.btnBack.setOnClickListener {
            pokemonList.clear()
            limit = binding!!.editPokemonCount.text.toString().toInt()
            Log.i("pokemon list limit",limit.toString())

            // returns the greater value of two. else, sets startIndex to 0
            startIndex = maxOf(startIndex - offset, 0)
            Log.i("pokemon list start index",startIndex.toString())

            getData(startIndex,limit)
        }

        binding!!.btnNext.setOnClickListener {
            pokemonList.clear()
            limit = binding!!.editPokemonCount.text.toString().toInt()

//            // returns the lesser value of two. else, sets startIndex to difference of maxPokemonCount and offset
//            startIndex = minOf(startIndex + offset, maxPokemonCount - offset)

            getData(startIndex,limit)
        }

        return view
    }

    private fun getData(startIndex: Int, limit: Int){
        val call: Call<PokemonListResponse> =
            PokemonAPIClient.getPokemonData.getList(startIndex, limit)

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

    private fun limitNotice(){
        Snackbar.make(requireView(),"Limit must be more than 0",Snackbar.LENGTH_SHORT).show()
    }
}