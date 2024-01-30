package com.scholarship.scholarshipapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.time.LocalDate
import java.time.LocalDateTime

class ScholarshipAdapter(val context: Context, val scholarshipList: ArrayList<Scholarship>) : BaseAdapter() {
    override fun getCount(): Int { // ListView에 표시될 항목의 총 개수를 반환.
        return scholarshipList.size
    }

    override fun getItem(position: Int): Any {
        return scholarshipList[position]
    }

    override fun getItemId(position: Int): Long {
        return scholarshipList[position].id.toLong()
    }

    private class ViewHolder {
        var scholarshipTitle: TextView? = null
        var scholarshipDesc: TextView? = null
        var scholarshipDDay: TextView? = null
        var scholarshipState: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("ScholarshipAdapter", "adapter 실행")
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.scholarship_list_item, parent, false)
            viewHolder = ViewHolder().apply {
                scholarshipTitle = view.findViewById(R.id.titleText)
                scholarshipDesc = view.findViewById(R.id.descText)
                scholarshipDDay = view.findViewById(R.id.d_dayText)
                scholarshipState = view.findViewById(R.id.stateText)
            }
            view.tag = viewHolder
            Log.d("ScholarshipAdapter", "View created for position: $position")
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
            Log.d("ScholarshipAdapter", "View reused for position: $position")
        }

        val scholarship = getItem(position) as Scholarship
        viewHolder.scholarshipTitle?.text = scholarship.title
        //viewHolder.scholarshipDesc?.text = scholarship.desc
        // 날짜 처리
        viewHolder.scholarshipDDay?.text = calculateDDay(scholarship.dday)

        return view
    }



    //---!현재날짜와 마감 날짜 비교... 현재 시간으로 비교하기 때문에 약간의 오류가 있음
     private fun calculateDDay(dday: String): String {
        //val currentDate: LocalDate = LocalDate.now()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // 날짜 형식 지정하기 위한 객체 생성
        val ddayDate = dateFormat.parse(dday) // 날짜 형식 date 객체로 파싱.
        val currentDate = Date() // 현재 날짜와 시간 나타냄

        // D-Day 계산
        val diff = ddayDate.time - currentDate.time
        val days = diff / (24 * 60 * 60 * 1000)

        return when {
            days > 0 -> "D-$days"
            days < 0 -> "D+${-days}"
            else -> "D-Day"
        }
    }
}