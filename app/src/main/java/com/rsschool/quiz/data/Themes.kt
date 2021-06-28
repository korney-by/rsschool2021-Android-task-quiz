package com.rsschool.quiz.data

import com.rsschool.quiz.R

object Themes {
    const val NO_THEME = -1
    fun getTheme(number: Int): Int {
        return if (number<=NO_THEME) {
            R.style.Theme_Quiz_Zero
        } else {
            when (number % 7) {
                0 -> R.style.Theme_Quiz_First
                1 -> R.style.Theme_Quiz_Second
                2 -> R.style.Theme_Quiz_Third
                3 -> R.style.Theme_Quiz_Fourth
                4 -> R.style.Theme_Quiz_Fifth
                5 -> R.style.Theme_Quiz_Sixth
                else -> R.style.Theme_Quiz_Seventh
            }
        }
    }
}