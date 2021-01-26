package com.example.logandpass

import android.os.Bundle
import android.system.Os.remove
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_card2.*
import kotlinx.android.synthetic.main.task_layout.*
import kotlinx.android.synthetic.main.task_layout.view.*

class ActivityCard2 : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = database.getReference("new task")
    private lateinit var mAuth: FirebaseAuth



   class myAdapter(private val values: List<String>): RecyclerView.Adapter<myAdapter.ViewHolder>(){


        override fun getItemCount() = values.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
            return ViewHolder(itemView)
        }


        class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){

            var textView: TextView? = null
            var myTextView: TextView? = null
            var mDel: Button? = null
            init {
                textView = itemView?.findViewById(R.id.et_new_task)
                myTextView = itemView?.findViewById(R.id.title_task)
                mDel = itemView?.findViewById(R.id.btn_del)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.myTextView?.text = values[position].toString()
            holder.itemView.btn_del.setOnClickListener {

            }


        }



    }



  /*  class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitleTask: TextView = itemView.findViewById<View>(R.id.title_task) as TextView
        var mDel: Button = itemView.findViewById<View>(R.id.btn_del) as Button

    }

   */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card2)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_task)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val param = et_new_task.text.toString()
        recyclerView.adapter = myAdapter(fillList())

        btn_add.setOnClickListener {

            myRef.child("new task").push().setValue(param)
            Toast.makeText(this, "New Task add complete", Toast.LENGTH_SHORT).show()
        }



     //   val myAdapter = FirebaseRecyclerAdapter(String::class.java, R.layout.task_layout, RecyclerView.ViewHolder::class.java, myRef.child("new taskDay"))


        /*    val adapter = FirebaseRecyclerAdapter(String::class.java,R.layout.task_layout,myAdapter.ViewHolder::class.java,myRef.child("new task")){


            fun populateViewHolder(viewHolder: TaskViewHolder, title: String?, position: Int) {
                viewHolder.mTitleTask.text = title
                viewHolder.mDel.setOnClickListener {
                    val itemRef: DatabaseReference = getRef(position)
                    itemRef.removeValue()
                }
            }
        }

     */








    }



    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..2).forEach { _ -> data.add("\$i element") }
        return data
    }


}