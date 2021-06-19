package com.rsschool.quiz

import androidx.fragment.app.Fragment

fun Fragment.Navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showFragmentQuiz(questonNumber: Int)
    fun showFragmentResult(resultPercent: Int)
}