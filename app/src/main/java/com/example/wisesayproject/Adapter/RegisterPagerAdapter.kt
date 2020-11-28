package com.example.wisesayproject.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wisesayproject.Fragment.Fragment1
import com.example.wisesayproject.Fragment.Fragment2
import com.example.wisesayproject.Fragment.Fragment3

class RegisterPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Fragment1()
            1 -> Fragment2()
            else -> Fragment3()
        }
    }

    override fun getCount() = 3
}