package com.example.logandpass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID:String = ""

    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()




        btn_reg.setOnClickListener {

            val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        btn_sign.setOnClickListener {



            loginUser()



        }


    }

    private fun loginUser() {
        val email = et_mail.text.toString()
        val password = et_pass.text.toString()
        when {
            email == "" -> {

                Toast.makeText(this," no login", Toast.LENGTH_SHORT).show()

            }
            password == "" -> {

                Toast.makeText(this," no pass", Toast.LENGTH_SHORT).show()

            }
            else -> {

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->

                    if (task.isSuccessful){

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        Toast.makeText(this,"IS SUCCESSFUL", Toast.LENGTH_SHORT).show()
                        finish()

                    } else {

                        Toast.makeText(this," error" + task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()

                    }
                }


            }
        }
    }



}