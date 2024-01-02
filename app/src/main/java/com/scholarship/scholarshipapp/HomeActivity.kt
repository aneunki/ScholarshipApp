package com.scholarship.scholarshipapp

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HomeActivity : AppCompatActivity() {
    private var scholarshipList = arrayListOf(
        Scholarship(1, "우리문화 장학재단", "조회수", -2),
        Scholarship(2, "2023년도 서울", "조회수", 1),
        Scholarship(3, "푸른등대", "조회수", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val textView1: TextView = findViewById(R.id.textview1)
        thread(start = true){
            try{
                val urlText = "http://172.30.1.72:8080/query.php"
                val url = URL(urlText)


                val netConn = url.openConnection() as HttpURLConnection
                netConn.readTimeout = 1500
                netConn.requestMethod = "GET"
                netConn.connectTimeout=5000
                netConn.doInput = true

                netConn.connect()

                Log.d("BasicSyntax","${netConn.responseCode}")
                if(netConn.responseCode == HttpURLConnection.HTTP_OK){
                    val streamReader = InputStreamReader(netConn.inputStream, "UTF-8")
                    val bufferedReader = BufferedReader(streamReader)

                    var sb = StringBuilder()

                    while(true){
                        val line = bufferedReader.readLine() ?: break
                        sb.append(line)
                    }

                    bufferedReader.close()
                    netConn.disconnect()

                    runOnUiThread{
                        textView1.text = sb.toString()
                    }

                }

            }catch(e:Exception){
                e.printStackTrace()
            }
        }

        val scholarshipListView: ListView = findViewById(R.id.scholarshipListView)

        val scholarshipAdapter = ScholarshipAdapter(this, scholarshipList)
        scholarshipListView.adapter = scholarshipAdapter

    }
}
