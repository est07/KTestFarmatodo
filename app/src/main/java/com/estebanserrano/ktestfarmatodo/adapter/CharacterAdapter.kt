package com.estebanserrano.ktestfarmatodo.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.estebanserrano.ktestfarmatodo.R
import com.estebanserrano.ktestfarmatodo.domain.model.Character
import com.estebanserrano.ktestfarmatodo.adapter.viewHolder.CharactersAdapterViewHolder
import com.estebanserrano.ktestfarmatodo.extension.inflate
import com.estebanserrano.ktestfarmatodo.listener.CharacterListener
import kotlin.properties.Delegates

class CharacterAdapter(data: List<Character> = emptyList(), val listener: CharacterListener) : RecyclerView.Adapter<CharactersAdapterViewHolder>() {

    var data by Delegates.observable(data) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapterViewHolder = CharactersAdapterViewHolder(parent.inflate(R.layout.character_cards_layout), listener)

    override fun onBindViewHolder(holder: CharactersAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


}