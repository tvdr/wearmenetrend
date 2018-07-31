package com.example.bartatibor.myapplication

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.wearable.activity.WearableActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import Json4Kotlin_Base
import android.view.View
import android.widget.Button
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.widget.ImageButton



class MainActivity : WearableActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        recyclerview_main.layoutManager = LinearLayoutManager(this)

        fetchJSON(recyclerview_main)


        val refreshbutton = findViewById(R.id.refreshbtn) as Button

        refreshbutton.setOnClickListener(View.OnClickListener {
            fetchJSON(recyclerview_main)
        })

    }


     fun fetchJSON(view:View){

        recyclerview_main.visibility = View.INVISIBLE

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
                    recyclerview_main.visibility = View.VISIBLE
                    recyclerview_main.adapter = MainAdapter(listdata)
                }

            }
        })


    }


}
