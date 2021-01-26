package com.example.logandpass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   lateinit var  auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        card1.setOnClickListener {
            intent = Intent(this, ActivityCard1::class.java)
            Toast.makeText(this, "JOB", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        card2.setOnClickListener {
            intent = Intent(this, ActivityCard2::class.java)
            startActivity(intent)
        }
        card3.setOnClickListener {
            intent = Intent(this, ActivityCard3::class.java)
            startActivity(intent)
        }
        card4.setOnClickListener {
            intent = Intent(this, ActivityCard4::class.java)
            startActivity(intent)
        }
    }

    private fun updateProfile(){

        auth.currentUser?.let {
            val username = et_mail.text
        }
    }
}