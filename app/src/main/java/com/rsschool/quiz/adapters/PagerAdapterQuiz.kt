package com.rsschool.quiz.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rsschool.quiz.interfaces.Navigator


class PagerAdapterQuiz(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val mainActivity = activity

    override fun createFragment(position: Int): Fragment {
        return Navigator().getFragmentQuiz(position)
    }

    override fun getItemCount(): Int {
        return Navigator().getCountPagesQuiz()
    }

}