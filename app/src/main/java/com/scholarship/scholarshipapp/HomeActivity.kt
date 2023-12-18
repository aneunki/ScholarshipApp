package com.scholarship.scholarshipapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private var scholarshipList = arrayListOf<Scholarship>(
        Scholarship(1, "우리문화 장학재단", "조회수", -2),
        Scholarship(2, "2023년도 서울", "조회수", 1),
        Scholarship(3, "푸른등대", "조회수", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val scholarshipListView: ListView = findViewById(R.id.scholarshipListView)

        val scholarshipAdapter = ScholarshipAdapter(this, scholarshipList)
        scholarshipListView.adapter = scholarshipAdapter

    }
}
