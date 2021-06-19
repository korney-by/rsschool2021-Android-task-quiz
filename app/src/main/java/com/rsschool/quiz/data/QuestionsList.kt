package com.rsschool.quiz.data

object QuestionsList {
    private data class Question(
        val text: String,
        val correctAnswer: Int,
        val answers: ArrayList<String>
    )

    private val questionsList = ArrayList<Question>()

    private fun add(text: String, correctAnswer: Int, answers: ArrayList<String>) {
        questionsList.add(Question(text, correctAnswer, answers))
    }

    fun getSize(): Int {
        return questionsList.size
    }

    private fun getQuestion(index: Int): Question {
        return questionsList[index]
    }

    fun getText(index: Int): String {
        return getQuestion(index).text
    }

    fun getCorrectAnswer(index: Int): Int {
        return getQuestion(index).correctAnswer
    }

    fun getAnswers(index: Int): ArrayList<String> {
        return getQuestion(index).answers
    }

    fun getResult(userAnswers: AnswersList): Int {
        var correctCount = 0
        for (i in 0 until QuestionsList.getSize()) {
            if (QuestionsList.getCorrectAnswer(i) == userAnswers.getAnswer(i)) {
                correctCount++
            }
        }
        return correctCount * 100 / QuestionsList.getSize()
    }

    init {
        add(
            "Сколько пальцев на руке?",
            2,
            arrayListOf(
                "Пальцев у человека 20",
                "A на руках 10",
                "Ой, а на одной 5",
                "Но у знакомого фрезировщика 3",
                "А на другой руке у него 4"
            )
        )
        add(
            "Что светит влюбленным по ночам?",
            1,
            arrayListOf("Солнце", "Луна", "Звезды", "Фонари", "Фингал под глазом","Статья 23.34")
        )
        add(
            "Кто снялся в роли робота Terminator-I ?",
            4,
            arrayListOf(
                "Сильвестр Сталоне",
                "Чак Норрис",
                "Дуэйн \"Скала\" Джонсон",
                "Михаил Пореченков",
                "Арнольд Шварцнегер",
                "Армян Джигарханян",
                "Евгений Герасимов",
                "Кто все эти люди?",
            )
        )
        add(
            "Что даёт корова?",
            3,
            arrayListOf("Мясо","Сметану","Кефир","Молоко", "Пиво", "Виски")
        )
        add(
            "Что ёжик несет на колючках?",
            5,
            arrayListOf(
                "Яблочко",
                "Грибочек",
                "Листья",
                "Котомку с пожитками",
                "Хорошие новости",
                "Ничего не несет, это все выдумки",
            )
        )
        add(
            " Куда на курортных пляжах просят не заплывать отдыхающих??",
            2,
            arrayListOf(" За горизонт", "За границу", "За буйки", "Замуж", "В камыши", "В дальние дали")
        )
    }
}

