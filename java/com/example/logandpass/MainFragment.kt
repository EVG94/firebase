package com.example.logandpass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.logandpass.R.id.btn_main
import kotlinx.android.synthetic.main.activity_card4.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment: Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      val Flate:View =  layoutInflater.inflate(R.layout.fragment_main, container, false)
        Flate.btn_main.setOnClickListener {

            val countString = tv2_main.text.toString()
            var count:Int = countString.toInt()
            count++
         Flate.tv2_main.text = count.toString()

        }


        return Flate
    }



}