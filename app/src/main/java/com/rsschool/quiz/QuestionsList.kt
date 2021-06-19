package com.rsschool.quiz

object QuestionsList {
    private val questionsList = ArrayList<Question>()

    fun getSize(): Int {
        return questionsList.size
    }

    private fun add(text: String, correctAnswer: Int, answers: ArrayList<String>) {
        questionsList.add(Question(text, correctAnswer, answers))
    }

    fun getQuestion(index: Int): Question {
        return questionsList[index]
    }

    init {
        add("Сколько пальцев на руке?", 2, arrayListOf("20", "10", "5", "3", "1"))
        add(
            "Что светит по ночам?",
            1,
            arrayListOf("Солнце", "Луна", "Звезды", "Фонари", "Фингал под глазом")
        )
        add(
            "Кто играл роль Terminator I?",
            4,
            arrayListOf(
                "Сильвестр Сталоне",
                "Чак Норрис",
                "Дуэйн \"Скала\" Джонсон",
                "Михаил Пореченков",
                "Арнольд Шварцнегер",
                "Кто все эти люди?"
            )
        )
        add(
            "Что даёт корова?",
            1,
            arrayListOf("Мясо", "Молоко", "Сметану", "Кефир", "Пиво", "Виски")
        )
    }
}

