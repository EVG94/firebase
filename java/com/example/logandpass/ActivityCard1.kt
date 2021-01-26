package com.example.logandpass

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_card1.*
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class ActivityCard1 : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = database.getReference("new task")
    private lateinit var mAuth: FirebaseAuth
    lateinit var mTask: SharedPreferences
    private var task: ArrayList<String> = ArrayList()
    private val taskID: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card1)


    //    loadData()


        myRef.keepSynced(true)

                //    val user: FirebaseUser = mAuth.currentUser!!

        var placeId: String?  = ""

        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice,
            task
        )
        lv1.adapter = adapter

        lv1.onItemClickListener = AdapterView.OnItemClickListener {

                adapterView: AdapterView<*>, view: View, position: Int, id: Long ->

            // получаем нажатый элемент
            val param: String? = adapter.getItem(position)
            if(lv1.isItemChecked(position)){
                if (param != null) {
                    taskID.add(param)
                }
            }
            else{

                taskID.remove(param)
            }
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val t: GenericTypeIndicator<List<String>>

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })
            //dobavlenie novogo elementa
        btnAdd.setOnClickListener {

            val param = et1.text.toString()

            placeId = myRef.push().key
            myRef.child(placeId!!).push().setValue(param)



            if(param.isNotEmpty() && !task.contains(param)){
                adapter.add(param)
                et1.setText("")

            }
         //   Toast.makeText(this, placeId, Toast.LENGTH_LONG).show()
        }
        bt_save.setOnClickListener {

            saveData()
        }


        btnRemove.setOnClickListener {
            for( i in 0 until taskID.size){
                adapter.remove(taskID[i])
            }

            lv1.clearChoices()
            taskID.clear()
        //    myRef.child(placeId.toString()).removeValue()

          myRef.child(placeId!!).removeValue()

            adapter.notifyDataSetChanged()

            Toast.makeText(this, "You task deleted", Toast.LENGTH_SHORT).show()

        }

    }

    private fun saveData() {
       val SP: SharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE)
        val editor:SharedPreferences.Editor = SP.edit()

        val gson:Gson = Gson()
        var json:String = gson.toJson(task)
        editor.putString("task list", json)
        editor.apply()
    }
  /*  private fun loadData(){

        val SP: SharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE)
        val gson:Gson = Gson()
        val json:String = SP.getString("task list", null).toString()

        val type = object : TypeToken<ArrayList<String?>?>() {}.type
        task = gson.fromJson(json, type)


    }

   */

    fun remove(view: View, position: Int) {



    }

}