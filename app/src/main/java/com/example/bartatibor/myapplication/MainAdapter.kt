package com.example.bartatibor.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.vonat_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>(){


    override fun getItemCount(): Int {
       return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.vonat_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder?.view?.from_allomas?.text = "Budapest";
        holder?.view?.to_allomas?.text = "Solymár";
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}