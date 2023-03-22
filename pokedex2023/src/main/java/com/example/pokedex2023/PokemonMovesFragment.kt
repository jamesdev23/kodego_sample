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
import com.example.pokedex2023.api.PokemonAPIClient
import com.example.pokedex2023.databinding.FragmentPokemonMovesBinding
import com.example.pokedex2023.models.PokemonInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonMovesFragment : Fragment() {
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            var message: String? = intent!!.getStringExtra("data")

            Log.i("Pokemon Info", message!!.toString())

            message?.let {
                getData(message.getPokemonID())
            }


        }
    }

    var binding: FragmentPokemonMovesBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonMovesBinding.inflate(inflater, container, false)
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

                getPokemonMoves(response)

                Log.d("API INFO CALL", response.name)
            }
        })
    }

    private fun getPokemonMoves(response: PokemonInfoResponse) {
        var pokemonMoves = response.moves
        var pokemonMoveList:ArrayList<String> = ArrayList()

        for(pokemonMove in pokemonMoves){
            pokemonMoveList.add(pokemonMove.move.name)
        }

        binding!!.pokemonMoves.text = "POKEMON MOVE:\n ${pokemonMoveList.joinToString(", ")}"
    }
}
