package com.example.pokedex2023.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex2023.databinding.ItemPokemonListRowBinding
import com.example.pokedex2023.models.Pokemon


class PokemonAdapter (private val context: Context,
                      private var pokemonList: ArrayList<Pokemon>)
    : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val itemBinding = ItemPokemonListRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        holder.bindItems(pokemonList[position])

        holder.btn_view_data.setOnClickListener {
            Log.d("Pokemon", "${pokemonList[position].name}")

            Intent().also{
                Log.d("Pokemon URL", "${pokemonList[position].url}")

                it.action = "ph.kodego.md2p.GETDATA"
                it.putExtra("data", pokemonList[position].url)
                context.sendBroadcast(it)
            }
        }
    }

    fun setList(pokemonList: ArrayList<Pokemon>){
        this.pokemonList.clear()
        this.pokemonList.addAll(pokemonList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ItemPokemonListRowBinding):
            RecyclerView.ViewHolder(itemBinding.root) {
        public var btn_view_data = itemBinding.btnViewData

        fun bindItems(pokemon: Pokemon) {
            itemBinding.pokemonName.text = pokemon.name
        }
    }

}