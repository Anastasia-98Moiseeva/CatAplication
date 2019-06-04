package com.example.test.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.test.R

class CatItem : Fragment() {

    private var position = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = this.arguments!!.getInt(message)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.cat_layout, container, false)

        val image = view.findViewById<ImageView>(R.id.imageView)

        Glide
            .with(context)
            .load(resources.getStringArray(R.array.url)[position])
            .into(image)

        val text = resources.getStringArray(R.array.names)[position]
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.setText(text)

        return view
    }
}

