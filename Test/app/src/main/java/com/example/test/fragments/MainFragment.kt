package com.example.test.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.ListAdapter
import com.example.test.R
import com.example.test.Router

val message = "mes"

class MainFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val buttons : RecyclerView = layout.findViewById(R.id.list)

        buttons.layoutManager = LinearLayoutManager(
            inflater.context, RecyclerView.VERTICAL, false
        )

        buttons.adapter = ListAdapter(createElements(), ::onElementClick)

        return layout
    }

    private fun createElements() : Array<String> {
        return resources.getStringArray(R.array.names)
    }

    private fun onElementClick(position : Int){
        router.navigateTo(true, ::CatItem, transportedMessage = position)
    }
}
