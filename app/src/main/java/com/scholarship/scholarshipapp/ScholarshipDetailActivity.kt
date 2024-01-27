package com.scholarship.scholarshipapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ScholarshipDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scholarship_detail)

        val scholarshipId = intent.getIntExtra("scholarship_id", 0)
        val scholarshipTitle = intent.getStringExtra("scholarship_title")

        Log.i("확인","Id : $scholarshipId, title : $scholarshipTitle")

        val detailTitle : TextView = findViewById(R.id.detailTitle)
        detailTitle.text = scholarshipTitle


    }
}