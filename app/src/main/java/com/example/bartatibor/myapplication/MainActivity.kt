package com.example.bartatibor.myapplication

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.wearable.activity.WearableActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import Json4Kotlin_Base
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        recyclerview_main.layoutManager = LinearLayoutManager(this)



        fetchJSON()

    }


    fun fetchJSON(){

        val today = SimpleDateFormat("yyyy.MM.dd").format(Date())


        val url = "http://apiv2.oroszi.net/elvira?from=solymar&to=nyugati&date=$today"
        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                println("JSON req failed")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()

                val listdata = gson.fromJson(body, Json4Kotlin_Base::class.java)



                runOnUiThread {
                    recyclerview_main.adapter = MainAdapter(listdata)
                }

            }
        })


    }


}


class HomeFeed(val videos:List<Video>)

class Video(val id: Int, val name:String)
