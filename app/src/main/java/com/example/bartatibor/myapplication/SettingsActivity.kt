package com.example.bartatibor.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val induloAllomas = intent.getStringExtra("indulo_allomas")
        val celAllomas = intent.getStringExtra("cel_allomas")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Enables Always-on
        setAmbientEnabled()

        runOnUiThread {
            from_allomas.setText(induloAllomas)
            to_allomas.setText(celAllomas)
        }

    }

    fun doSave(v:View){
        val induloAllomas = from_allomas.text
        val celAllomas = to_allomas.text

        val intent = Intent(this,MainActivity::class.java).apply{
            putExtra("indulo_allomas",induloAllomas.toString())
            putExtra("cel_allomas",celAllomas.toString())
        }
        startActivity(intent)
    }


}

