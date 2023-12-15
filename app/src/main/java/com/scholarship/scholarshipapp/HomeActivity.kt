package com.scholarship.scholarshipapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}

class ScholarshipData(
    val id: Int,
    val title: String,
    val d_day: Int
) {

}