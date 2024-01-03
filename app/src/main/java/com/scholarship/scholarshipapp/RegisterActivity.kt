package com.scholarship.scholarshipapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.scholarship.scholarshipapp.databinding.ActivityMainBinding
//import com.scholarship.scholarshipapp.databinding.ActivityMainBinding
import com.scholarship.scholarshipapp.databinding.ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {
    val TAG : String = "Register"
    var isExistBlank = false
    var isPWSame = false
    private lateinit var binding: ActivityRegisterBinding // activitymainBinding 객체의 변수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent = Intent(this, HomeActivity::class.java) //intent로 화면전환

        binding.btnJoinCheck.setOnClickListener { // 버튼 클릭하면

            val id = binding.joinID.text.toString()
            val pw = binding.joinPassword.text.toString()
            val check = binding.passwordCheck.text.toString()

            // 유저가 항목을 다 채우지 않았을 경우
            if(id.isEmpty() || pw.isEmpty() || check.isEmpty()){
                isExistBlank = true
            }
            else{
                if(pw == check){
                    isPWSame = true
                }
            }

        }
    }
}