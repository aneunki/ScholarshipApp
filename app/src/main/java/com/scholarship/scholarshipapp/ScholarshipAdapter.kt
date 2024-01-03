package com.scholarship.scholarshipapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ScholarshipAdapter(val context: Context, val scholarshipList: ArrayList<Scholarship>) : BaseAdapter() {
    override fun getCount(): Int {
        return scholarshipList.size
    }

    override fun getItem(position: Int): Any {
        return scholarshipList[position]
    }

    override fun getItemId(position: Int): Long {
        return scholarshipList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.scholarship_list_item, null)

        val scholarshipTitle = view.findViewById<TextView>(R.id.titleText)
        val scholarshipDesc = view.findViewById<TextView>(R.id.descText)
        val scholarshipDDay = view.findViewById<TextView>(R.id.d_dayText)
        val scholarshipState = view.findViewById<TextView>(R.id.stateText)

        val scholarship = scholarshipList[position]
        scholarshipTitle.text = scholarship.title
        scholarshipDesc.text = scholarship.desc
        if(scholarship.d_day==0){
            scholarshipDDay.text = "D-Day"
        }else if(scholarship.d_day>0){
            scholarshipDDay.text = "D+${scholarship.d_day}"
        }else{ scholarshipDDay.text = "D${scholarship.d_day}"}

        if(scholarship.d_day<=0){
            scholarshipState.text = "모집중"
        }else{
            scholarshipState.text = "모집마감"
        }

        return view
    }
}