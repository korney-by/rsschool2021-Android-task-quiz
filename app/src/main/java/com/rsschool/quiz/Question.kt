package com.rsschool.quiz

data class Question(val text: String, val correctAnswer: Int, val answers: ArrayList<String>) {
    //val text: String = _text
    //val answers: ArrayList<String> = _answers.toCollection()
    //val correctAnswer = if (_correctAnswer in 0 until answers.count()) _correctAnswer else 0
}
