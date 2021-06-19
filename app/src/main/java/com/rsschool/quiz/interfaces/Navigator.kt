package com.rsschool.quiz.interfaces

import androidx.fragment.app.Fragment

interface Navigator {
    fun restartQuiz()
    fun showFragmentQuiz(questionNumber: Int)
    fun showFragmentResult(resultPercent: Int)
}

fun Fragment.Navigator(): Navigator {
    return requireActivity() as Navigator
}