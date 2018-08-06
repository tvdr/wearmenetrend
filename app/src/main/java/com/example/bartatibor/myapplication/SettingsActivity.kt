package com.example.bartatibor.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.File


class SettingsActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var settingsIndulo = ""
        if (File(applicationContext.filesDir, "settings_indulo").exists()){
            settingsIndulo = File(applicationContext.filesDir, "settings_indulo").bufferedReader().use { it.readText().trim(); }
        }


        var settingsCel = ""
        if (File(applicationContext.filesDir,"settings_cel").exists()){
            settingsCel = File(applicationContext.filesDir, "settings_cel").bufferedReader().use { it.readText().trim(); }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Enables Always-on
        setAmbientEnabled()

        runOnUiThread {
            from_allomas.setText(settingsIndulo)
            to_allomas.setText(settingsCel)
        }

    }

    fun doSave(v:View){
        val induloAllomas = from_allomas.text
        val celAllomas = to_allomas.text


        File(applicationContext.filesDir, "settings_indulo").printWriter().use { out ->
            out.println("$induloAllomas")
        }

        File(applicationContext.filesDir, "settings_cel").printWriter().use { out ->
            out.println("$celAllomas")
        }

        val intent = Intent(this,MainActivity::class.java).apply{
            //putExtra("indulo_allomas",induloAllomas.toString())
            //putExtra("cel_allomas",celAllomas.toString())
        }
        startActivity(intent)
    }

    fun setVisszaut(v:View){

        val induloAllomas = from_allomas.text
        val celAllomas = to_allomas.text

        from_allomas.text = celAllomas
        to_allomas.text = induloAllomas

    }


}

