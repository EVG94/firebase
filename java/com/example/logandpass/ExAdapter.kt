package com.example.logandpass

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_ex.*
import kotlinx.android.synthetic.main.single_ex.view.*

class ExAdapter(private val exList: List<EXitem>,

                private val listener: OnItemClickListener
                ): RecyclerView.Adapter<ExAdapter.ExViewHolder>() {

   inner class ExViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val mbtn:Button = itemView.btn_del
        val mtv1:TextView = itemView.tv1
        val mtv2:TextView = itemView.tv2

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_ex,
        parent, false)

        return ExViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExViewHolder, position: Int) {
        val currentItem = exList[position]

        holder.mtv1.text = currentItem.text1
        holder.mtv2.text = currentItem.text2



    }

    override fun getItemCount() = exList.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}