package com.example.ileriandroid

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView

class CleanCodePage(private val context: Context, private val listView: ListView, private val listItems: List<String>) {

    private lateinit var arrayAdapter: ArrayAdapter<String>

    init {
        setListView()
    }

    private fun setListView() {
        arrayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = arrayAdapter
    }
    //  listeyi filtrelemek için kullanılacak metıt
    fun filterList(newText: String?) {
        arrayAdapter.filter.filter(newText)
    }
    // Listener'ı ayarlamak için kullanılacak metot
    fun setOnItemClickListener(listener: (String) -> Unit) {
        listView.setOnItemClickListener { _, _, position, _ ->
            listener(listItems[position])
        }
    }
}
