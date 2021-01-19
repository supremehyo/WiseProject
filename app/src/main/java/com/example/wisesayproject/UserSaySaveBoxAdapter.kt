package com.example.wisesayproject

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.wisesayproject.Activity.UserSayingBoxDetailActivity
import com.example.wisesayproject.Activity.UserSayingSaveBoxDetailActivity
import com.example.wisesayproject.DTO.SaveWise
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_1.view.*
import kotlinx.android.synthetic.main.usersaysaveitem.view.*
import java.util.*

class UserSaySaveBoxAdapter(private var context : Context?, private var SayList : List<SaveWise>): BaseAdapter() {



    private class ViewHolder{
        var savecontent : TextView? = null
        var  img_listsaveitem : ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if(convertView ==null){
            holder = ViewHolder()
            val saveWise : SaveWise = SayList[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.usersaysaveitem,null)
            holder.img_listsaveitem = view.findViewById(R.id.img_listsaveitem)
            holder.savecontent?.text = saveWise.myquestion
            Picasso.get().load("http://121.181.189.167:8090/upload/"+saveWise.imagename).into(holder.img_listsaveitem)
            view.setOnClickListener {
                val intent = Intent(context, UserSayingSaveBoxDetailActivity::class.java)
                intent.putExtra("writeusernickname",saveWise.writeusernickname )
                intent.putExtra("content", saveWise.content)
                intent.putExtra("backgroundname" , saveWise.imagename);
                context!!.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }

            view.tag= holder
            /* convertView가 null, 즉 최초로 화면을 실행할 때에
            ViewHolder에 각각의 TextView와 ImageView를 findVidwById로 설정.
            마지막에 태그를 holder로 설정한다. */
        }else {
            holder = convertView.tag as ViewHolder
            view = convertView
            /* 이미 만들어진 View가 있으므로, tag를 통해 불러와서 대체한다. */
        }

        return view
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