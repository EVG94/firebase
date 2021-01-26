package com.example.logandpass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID:String = ""
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mAuth = FirebaseAuth.getInstance()


        btn_reg1.setOnClickListener {


            registerUser()
        }


    }
    private fun registerUser() {

        val email = et_mail1.text.toString()
        val password = et_pass1.text.toString()
        when {
            email == "" -> {

                Toast.makeText(this," no login", Toast.LENGTH_SHORT).show()

            }
            password == "" -> {

                Toast.makeText(this," no pass", Toast.LENGTH_SHORT).show()

            }
            else -> {

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful){

                        firebaseUserID = mAuth.currentUser!!.uid
                        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID


                        refUsers.updateChildren(userHashMap).addOnCompleteListener {task ->

                            if (task.isSuccessful) {

                                val intent =
                                    Intent(this@RegistrationActivity, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                                Toast.makeText(this, "Registration complete", Toast.LENGTH_SHORT).show()
                            }


                        }

                    }else {

                        Toast.makeText(this," Error" + task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()


                    }
                }

            }
        }

    }
}