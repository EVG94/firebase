package com.example.logandpass

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.firebase.ui.database.FirebaseListAdapter



import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_card3.*
import java.util.ArrayList
import kotlin.jvm.internal.Ref
import kotlin.random.Random


open class ActivityCard3 : AppCompatActivity(), ExAdapter.OnItemClickListener {

   private val ExList = generateList(6)
    private val adapter = ExAdapter(ExList, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card3)


        rv_list_task.adapter = adapter
        rv_list_task.layoutManager = LinearLayoutManager(this)
        rv_list_task.setHasFixedSize(true)




    }
    fun insertItem(view: View){

        val index = Random.nextInt(8)
        val newItem = EXitem("New item position $index", "Line 2")
        ExList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }
    fun removeItem(view: View){

        val index = Random.nextInt(4)
        ExList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position click", Toast.LENGTH_SHORT).show()
        val clickItem = ExList[position]
        clickItem.text1 = "Clicked"
    adapter.notifyItemChanged(position)
    }

    private fun generateList(size:Int): ArrayList<EXitem> {
        val data = ArrayList<EXitem>()
        for (i in 0 until size){
            val item  = EXitem("Item $i", "line2")
            data+=item
        }
        return data
    }
}