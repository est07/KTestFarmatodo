package com.estebanserrano.ktestfarmatodo.adapter.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.estebanserrano.ktestfarmatodo.domain.model.Character
import com.estebanserrano.ktestfarmatodo.extension.getImageByUrl
import com.estebanserrano.ktestfarmatodo.listener.CharacterListener
import kotlinx.android.synthetic.main.character_cards_layout.view.*

class CharactersAdapterViewHolder(view: View, val listener: CharacterListener) : RecyclerView.ViewHolder(view) {

    fun bind(item: Character) = with(itemView) {
        txv_item.text = item.name
        val string = item.thumbnail.path + "." + item.thumbnail.extension
        image_thumbnail.getImageByUrl(string)
        setOnClickListener { listener(item) }
    }
}