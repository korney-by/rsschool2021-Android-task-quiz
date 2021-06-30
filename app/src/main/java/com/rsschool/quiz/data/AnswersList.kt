package com.rsschool.quiz.data

object AnswersList {
    private const val NO_ANSWER = -1
    private val answerList: IntArray = IntArray(QuestionsList.count) { NO_ANSWER }
    private var hasAnswers = NO_ANSWER

    fun isNoAnswer(index: Int): Boolean {
        return (answerList[index] == NO_ANSWER)
    }

    fun getAnswer(index: Int): Int {
        return answerList[index]
    }

    fun setAnswer(index: Int, value: Int) {
        answerList[index] = value
        if (index > hasAnswers) {
            hasAnswers = index
        }
    }

    private fun revokeOne(index: Int) {
        answerList[index] = NO_ANSWER
    }

    fun getHasAnswers():Int {
        return (hasAnswers + 1)
    }

    fun revokeAll() {
        hasAnswers = NO_ANSWER
        for (i in answerList.indices) {
            revokeOne(i)
        }
    }
}
