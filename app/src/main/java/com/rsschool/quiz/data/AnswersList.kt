package com.rsschool.quiz.data

object AnswersList {
    private const val NO_ANSWER = -1
    private val answerList: IntArray = IntArray(QuestionsList.getSize()) { NO_ANSWER }

    fun isNoAnswer(index:Int):Boolean{
        return (answerList[index] == NO_ANSWER)
    }

    fun getAnswer(index: Int): Int {
        return answerList[index]
    }

    fun setAnswer(index: Int, value: Int) {
        answerList[index] = value
    }

    fun revokeOne(index: Int) {
        answerList[index] = NO_ANSWER
    }

    fun revokeAll() {
        for (i in 0 until answerList.size) {
            revokeOne(i)
        }
    }
}
