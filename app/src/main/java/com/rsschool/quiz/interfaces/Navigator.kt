package com.rsschool.quiz.interfaces

import androidx.fragment.app.Fragment
import com.rsschool.quiz.adapters.PagerAdapterQuiz
import com.rsschool.quiz.adapters.PagerAdapterResult

interface Navigator {
    fun restartQuiz()
    fun showFragmentQuiz(questionNumber: Int)
    fun showResult()

    fun getFragmentQuiz(position:Int):Fragment
    fun getFragmentResult():Fragment
    fun onSetNewAnswer(position: Int)
    fun getCountPagesQuiz():Int
}

fun Fragment.Navigator(): Navigator {
    return requireActivity() as Navigator
}

fun PagerAdapterQuiz.Navigator(): Navigator {
    return mainActivity as Navigator
}

fun PagerAdapterResult.Navigator(): Navigator {
    return mainActivity as Navigator
}