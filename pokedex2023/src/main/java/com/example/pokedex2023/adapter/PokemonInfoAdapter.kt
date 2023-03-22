package com.example.pokedex2023.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PokemonInfoAdapter (manager: FragmentManager): FragmentPagerAdapter(manager){
    private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
    private val fragmentTitleList: ArrayList<String> = ArrayList<String>()

    override fun getCount() = fragmentList.size

    override fun getItem(position: Int) = fragmentList[position]

    override fun getPageTitle(position: Int) = fragmentTitleList[position]

    fun add(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}