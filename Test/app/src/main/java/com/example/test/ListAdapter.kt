package com.example.test

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ListAdapter(private val names : Array<String>, private val onClick : (Int) -> Unit)
    : RecyclerView.Adapter<ElementHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ElementHolder {

        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.button_layout,
            viewGroup, false)

        val img : ImageView = rootView.findViewById(R.id.icon)
        val text : TextView = rootView.findViewById(R.id.title)
        val button : CardView = rootView.findViewById(R.id.button)
        return ElementHolder(rootView, img, text, button, onClick)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ElementHolder, pos: Int) {
        holder.text.text = names[pos]
        Glide
            .with(holder.itemView.getContext())
            .load(holder.itemView.resources.getStringArray(R.array.url)[pos])
            .into(holder.img)
    }
}

class ElementHolder(view : View, val img : ImageView, val text : TextView, val button: CardView, val onClick : (Int) -> Unit)
    : RecyclerView.ViewHolder(view) {

    init {
        button.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}