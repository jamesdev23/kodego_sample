package com.example.pokedex2023

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex2023.databinding.FragmentPokemonImageBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso


class PokemonImageFragment : Fragment() {

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            //filter action
            var imageUrl: String? = intent!!.getStringExtra("data")
            var imageShinyUrl: String? = intent.getStringExtra("data2")

            //if(imageURL != null)
            imageUrl?.let{
                Picasso
                    .with(activity!!.applicationContext)
                    .load(it)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.egg)
                    .error(R.drawable.egg)
                    .into(binding!!.pokemonImage)
            }

            imageShinyUrl?.let{
                Picasso
                    .with(activity!!.applicationContext)
                    .load(it)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.egg)
                    .error(R.drawable.egg)
                    .into(binding!!.pokemonImageShiny)
            }

        }
    }

    var binding: FragmentPokemonImageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonImageBinding.inflate(inflater, container, false)
        var view = binding!!.root

        return view
    }

    override fun onDestroyView() {
        requireActivity().unregisterReceiver(receiver)
        super.onDestroyView()
    }

    private fun setupReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.kodego.md2p.LOADIMAGEACTION")
        requireActivity().registerReceiver(receiver, intentFilter)
    }
}