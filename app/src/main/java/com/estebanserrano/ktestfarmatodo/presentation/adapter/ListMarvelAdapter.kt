package com.estebanserrano.ktestfarmatodo.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.estebanserrano.ktestfarmatodo.R
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.presentation.adapter.viewHolder.ListMarvelAdapterViewHolder
import com.estebanserrano.ktestfarmatodo.presentation.extension.inflate
import com.estebanserrano.ktestfarmatodo.presentation.listener.ListMarvelListener
import kotlin.properties.Delegates

class ListMarvelAdapter(data: List<MarvelCard> = emptyList(), val listener: ListMarvelListener) : RecyclerView.Adapter<ListMarvelAdapterViewHolder>() {

    var data by Delegates.observable(data) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMarvelAdapterViewHolder = ListMarvelAdapterViewHolder(parent.inflate(R.layout.character_cards_layout), listener)

    override fun onBindViewHolder(holder: ListMarvelAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


}