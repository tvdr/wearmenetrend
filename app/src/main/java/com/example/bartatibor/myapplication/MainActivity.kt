package com.example.bartatibor.myapplication

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        recyclerview_main.layoutManager = LinearLayoutManager(this)
        recyclerview_main.adapter = MainAdapter()


        fetchJSON()

    }


    fun fetchJSON(){
        println("Attempting to fetch json")
    }


}
