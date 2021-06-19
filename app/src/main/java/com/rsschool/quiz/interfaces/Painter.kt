package com.rsschool.quiz.interfaces

import androidx.fragment.app.Fragment

 interface Painter {
    fun applyTheme(number: Int)
}

fun Fragment.Painter(): Painter {
    return requireActivity() as Painter
}