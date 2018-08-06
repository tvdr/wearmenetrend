package com.example.bartatibor.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.vonat_row.view.*
import Json4Kotlin_Base
import android.graphics.Color

class MainAdapter(val listdata: Json4Kotlin_Base): RecyclerView.Adapter<CustomViewHolder>(){


    override fun getItemCount(): Int {
       return listdata.timetable.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.vonat_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.starttime?.text = listdata.timetable[position].details[0].dep
        holder.view.destinationtime?.text = listdata.timetable[position].details[1].dep

        holder.view.startreal?.text = listdata.timetable[position].details[0].dep_real
        holder.view.depreal?.text = listdata.timetable[position].details[1].dep_real

        holder.view.fromtext.text = listdata.timetable[position].details[0].from
        holder.view.totext.text = listdata.timetable[position].details[1].from
        holder.view.datetext.text = listdata.date



        if (listdata.timetable[position].details[0].dep != listdata.timetable[position].details[0].dep_real){
            holder.view.startreal?.setTextColor(Color.parseColor("#FF0000"))
        }else{
            holder.view.startreal?.setTextColor(Color.parseColor("#FFFFFF"))
        }

        if (listdata.timetable[position].details[1].dep != listdata.timetable[position].details[1].dep_real){
            holder.view.depreal?.setTextColor(Color.parseColor("#FF0000"))
        }else{
            holder.view.depreal?.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}