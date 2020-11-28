package com.example.wisesayproject.Fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.example.wisesayproject.R
import kotlinx.android.synthetic.main.fragment_1.view.*

class Fragment1 : Fragment() {

    lateinit var gd: GestureDetector
    lateinit var listener: GestureDetector.OnDoubleTapListener
    private var doubletapfrage: Long = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        view.iv_fragment1img.setOnClickListener {
            Log.d("cccclink" , "짧은 클릭")
        }
        view.iv_fragment1img.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                Log.d("cccclink" , "롱클릭")

                return true
            }

        })
        return view
    }
}