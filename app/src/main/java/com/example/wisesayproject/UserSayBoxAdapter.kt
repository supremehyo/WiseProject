package com.example.wisesayproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wisesayproject.Activity.UserSayingBoxDetailActivity
import com.example.wisesayproject.DTO.Board
import kotlinx.android.synthetic.main.usersayitem.view.*

class UserSayBoxAdapter(private var context : Context?, private var SayList : List<Board>):BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val board : Board = SayList[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val usersayView = inflater.inflate(R.layout.usersayitem,null)
        usersayView.content.text = board.content

        usersayView.setOnClickListener {
            val intent = Intent(context, UserSayingBoxDetailActivity::class.java)
            intent.putExtra("name",board.userid )
            intent.putExtra("content", board.content)
            context!!.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))


        }

        return usersayView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        return SayList.size
    }
}