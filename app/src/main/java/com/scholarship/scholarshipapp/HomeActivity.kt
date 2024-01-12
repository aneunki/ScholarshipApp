package com.scholarship.scholarshipapp

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class HomeActivity : AppCompatActivity() {

    private lateinit var scholarshipListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        scholarshipListView = findViewById(R.id.scholarshipListView)

        CoroutineScope(Dispatchers.IO).launch {
            val scholarshipList = fetchScholarshipData()
            withContext(Dispatchers.Main) {
                if (scholarshipList != null) {
                    val scholarshipAdapter = ScholarshipAdapter(this@HomeActivity, scholarshipList)
                    scholarshipListView.adapter = scholarshipAdapter
                } else {
                    Toast.makeText(this@HomeActivity, "데이터를 불러오는데 실패했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private suspend fun fetchScholarshipData(): ArrayList<Scholarship>? {
        return try {
            val url = URL("http://220.94.225.195/query.php")
            val netConn = url.openConnection() as HttpURLConnection

            netConn.run {
                readTimeout = 1500
                connectTimeout = 5000
                requestMethod = "GET"
                doInput = true

                connect()

                if (netConn.responseCode == HttpURLConnection.HTTP_OK) {
                    val rawJson = netConn.inputStream.bufferedReader().use { it.readText() }
                    val type = object : TypeToken<ScholarshipData>() {}.type
                    val scholarshipData = Gson().fromJson<ScholarshipData>(rawJson, type)
                    return scholarshipData.webnautes
                } else {
                    return null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
