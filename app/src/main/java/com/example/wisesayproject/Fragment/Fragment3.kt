package com.example.wisesayproject.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.fragment_1.view.*
import kotlinx.android.synthetic.main.fragment_3.view.*


class Fragment3 : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)
        view.f3root.setOnClickListener {
            Log.d("cccclink" , "짧은 클릭")
        }
        view.f3root.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                Log.d("cccclink" , "롱클릭")

                return true
            }

        })

        return  view
    }


}