package com.estebanserrano.ktestfarmatodo.presentation.adapter.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.presentation.extension.getImageByUrl
import com.estebanserrano.ktestfarmatodo.presentation.listener.ListMarvelListener
import kotlinx.android.synthetic.main.character_cards_layout.view.*

class ListMarvelAdapterViewHolder(view: View, val listener: ListMarvelListener) : RecyclerView.ViewHolder(view) {

    fun bind(item: MarvelCard) = with(itemView) {
        txv_item.text = item.header

        val string = item.thumbnail.path + "." + item.thumbnail.extension
        image_thumbnail.getImageByUrl(string)


        setOnClickListener { listener(item) }
    }
}