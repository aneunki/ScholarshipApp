package com.scholarship.scholarshipapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.ims.RegistrationManager.RegistrationCallback
import com.scholarship.scholarshipapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // activitymainBinding 객체의 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // xml 화면 뷰 연결

        //뷰 바인딩 초기화 각각의 위젯을 위젯에 binding.아이디로 접근할 수 있게 함
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent_login = Intent(this, HomeActivity::class.java) //intent로 화면전환
        val intent_join = Intent(this, RegisterActivity::class.java) //intent로 화면전환

        binding.btnLogin.setOnClickListener { // 버튼 클릭하면
           
            startActivity(intent_login) // intent 객체를 시작
        }

        binding.btnJoin.setOnClickListener { // 버튼 클릭하면

            startActivity(intent_join) // intent 객체를 시작
        }


    }
}