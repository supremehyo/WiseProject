package com.example.wisesayproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.wisesayproject.DTO.User
import kotlinx.android.synthetic.main.list_item_user.view.*
import java.util.ArrayList

class UserAdapter (val context: Context,val UserList:ArrayList<User>): BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_user,null)

        return view
    }

    override fun getItem(position: Int): Any {
        return UserList[position]
    }

    override fun getItemId(position: Int): Long {
    return  0
    }

    override fun getCount(): Int {
        return UserList.size
    }

}