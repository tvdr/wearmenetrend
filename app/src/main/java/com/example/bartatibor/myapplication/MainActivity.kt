package com.example.bartatibor.myapplication

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.wearable.activity.WearableActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import Json4Kotlin_Base
import android.content.Intent
import android.support.wear.widget.drawer.WearableActionDrawerView
import android.view.MenuItem
import android.view.View
import android.widget.Button
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.widget.ImageButton



class MainActivity : WearableActivity(), MenuItem.OnMenuItemClickListener {


    var induloAllomas : String =  "Solymár"
    var celAllomas : String = "Nyugati"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        recyclerview_main.layoutManager = LinearLayoutManager(this)

        val mWearableActionDrawer = findViewById(R.id.bottom_action_drawer) as WearableActionDrawerView
        mWearableActionDrawer.getController().peekDrawer()
        mWearableActionDrawer.setOnMenuItemClickListener(this)




        val settingsIndulo = intent.getStringExtra("indulo_allomas")
        val settingsCel = intent.getStringExtra("cel_allomas")

        if(settingsIndulo != "" && settingsIndulo != null) {
            induloAllomas = settingsIndulo
        }

        if (settingsCel != "" && settingsCel != null) {
            celAllomas = settingsCel
        }

        fetchJSON(recyclerview_main,induloAllomas,celAllomas)

    }


    override fun onMenuItemClick(menuItem: MenuItem): Boolean {
        val itemId = menuItem.itemId

        when (itemId){
            R.id.menu_refresh -> {
                fetchJSON(View(this))
                val mWearableActionDrawer = findViewById(R.id.bottom_action_drawer) as WearableActionDrawerView
                mWearableActionDrawer.getController().closeDrawer()

            }
            R.id.menu_settings -> {
                val intent = Intent(this,SettingsActivity::class.java).apply{
                    putExtra("indulo_allomas",induloAllomas)
                    putExtra("cel_allomas",celAllomas)
                }
                startActivity(intent)
            }
        }

        return true
    }

     fun fetchJSON(view:View, induloAllomas :String = this.induloAllomas,celAllomas:String = this.celAllomas){

         println("FETCHJSON")


         recyclerview_main.visibility = View.GONE
         progressBar.visibility = View.VISIBLE


        val today = SimpleDateFormat("yyyy.MM.dd").format(Date())
        val url = "http://apiv2.oroszi.net/elvira?from=$induloAllomas&to=$celAllomas&date=$today"

         println(url)

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
                    progressBar.visibility = View.INVISIBLE
                }

            }
        })


    }


}
