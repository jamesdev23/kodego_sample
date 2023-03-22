package com.example.pokedex2023

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex2023.databinding.FragmentPokemonInfoBinding
import com.example.pokedex2023.api.PokemonAPIClient
import com.example.pokedex2023.models.PokemonInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonInfoFragment : Fragment() {
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            var message: String? = intent!!.getStringExtra("data")

            Log.i("Pokemon Info", message!!.toString())

            message?.let {
                getData(message.getPokemonID())
            }


        }
    }

    var binding: FragmentPokemonInfoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
        var view = binding!!.root

        return view
    }

    override fun onDestroyView() {
        requireActivity().unregisterReceiver(receiver)
        super.onDestroyView()
    }

    private fun setupReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.kodego.md2p.GETDATA")
        requireActivity().registerReceiver(receiver, intentFilter)
    }

    private fun getData(id: Int){
        val call: Call<PokemonInfoResponse> = PokemonAPIClient.getPokemonData.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonInfoResponse> {
            override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable) {
                Log.d("API CALL", "Failed API CALL")
            }

            override fun onResponse(
                call: Call<PokemonInfoResponse>,
                response: Response<PokemonInfoResponse>
            ) {
                var response: PokemonInfoResponse = response!!.body()!!


                Intent().also {
                    Log.d("Pokemon", "${response.sprites.front_default}")
                    Log.d("Pokemon", "${response.sprites.front_shiny}")
                    it.setAction("ph.kodego.md2p.LOADIMAGEACTION")
                    it.putExtra("data", response.sprites.front_default)
                    it.putExtra("data2", response.sprites.front_shiny)
                    context!!.sendBroadcast(it)
                }

                getPokemonInfos(response)

                Log.d("API INFO CALL", response.name)
            }
        })
    }

    private fun getPokemonInfos(response: PokemonInfoResponse){
        var pokemonTypes = response.types
        var pokemonTypeList: ArrayList<String> = ArrayList()
        var pokemonAbilities = response.abilities
        var pokemonAbilityList: ArrayList<String> = ArrayList()

        binding!!.pokemonId.text = "POKEMON ID:\n ${response.id}"
        binding!!.pokemonName.text = "POKEMON NAME:\n ${response.name}"
        binding!!.pokemonHeight.text = "POKEMON HEIGHT:\n ${response.height}"
        binding!!.pokemonWeight.text = "POKEMON WEIGHT:\n ${response.weight}"


        for (pokemonType in pokemonTypes) {
            pokemonTypeList.add(pokemonType.type.name)
        }
        binding!!.pokemonType.text = "POKEMON TYPE/S:\n ${pokemonTypeList.joinToString(", ")}"

        for (pokemonAbility in pokemonAbilities) {
            pokemonAbilityList.add(pokemonAbility.ability.name)
        }
        binding!!.pokemonAbilities.text = "POKEMON ABILITIES:\n ${pokemonAbilityList.joinToString(", ")}"
    }
}
