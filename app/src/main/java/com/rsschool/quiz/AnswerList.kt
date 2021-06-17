package com.rsschool.quiz

object AnswerList {
    const val NO_ANSWER = -1
    private val answerList: IntArray = IntArray(QuestionsList.getSize()) { NO_ANSWER }


    fun getSize(): Int {
        return answerList.size
    }

    fun getAnswer(index: Int): Int {
        return answerList[index]
    }

    fun setAnswer(index: Int, value: Int) {
        answerList[index] = value
    }

    fun revokeOne(index:Int){
        answerList[index] = NO_ANSWER
    }

    fun revokeAll() {
        for (i in answerList) {
            answerList[i] = NO_ANSWER
        }
    }
}
