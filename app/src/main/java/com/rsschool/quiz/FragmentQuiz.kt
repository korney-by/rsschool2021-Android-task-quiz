package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FragmentQuiz : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var questionNumber = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            questionNumber = it.getInt(NUMBER_QUESTION)
        }

        loadQuestion()
        showActualData()


        binding.nextButton.setOnClickListener {
            saveAnswerState()
            loadQuestion(1)
            showActualData()
        }

        binding.previousButton.setOnClickListener {
            saveAnswerState()
            loadQuestion(-1)
            showActualData()
        }


        binding.quizToolbar.setNavigationOnClickListener{
            binding.previousButton.callOnClick()
        }
    }


    private fun saveAnswerState(){
        AnswerList.setAnswer(questionNumber, binding.radioGroupAnswers.checkedRadioButtonId)
    }

    private fun showActualData() {
        setEnabledButtons()
        binding.quizToolbar.title = "Вопрос ${questionNumber + 1}"

    }

    private fun setEnabledButtons() {
        binding.nextButton.isEnabled = (questionNumber < QuestionsList.getSize() - 1)
        binding.previousButton.isEnabled = (questionNumber > 0)
    }


    private fun loadQuestion(shift: Int = 0) {
        if (questionNumber + shift !in 0 until QuestionsList.getSize()) {
            return
        }
        questionNumber += shift
        val question = QuestionsList.getQuestion(questionNumber)

        binding.question.text = question.text
        binding.radioGroupAnswers.clearCheck()
        binding.radioGroupAnswers.removeAllViews()

        for (i in 0 until question.answers.size) {
            val radioButtonAnswer = MaterialRadioButton(binding.radioGroupAnswers.context)
            val e = question.answers[i]
            radioButtonAnswer.text = e
            radioButtonAnswer.id = i
            binding.radioGroupAnswers.addView(radioButtonAnswer)
        }
        if (AnswerList.getAnswer(questionNumber) != AnswerList.NO_ANSWER) {
            binding.radioGroupAnswers.check(AnswerList.getAnswer(questionNumber))
        }

    }

    companion object {
        private const val NUMBER_QUESTION = "NUMBER_QUESTION"

        @JvmStatic
        fun newInstance(_questonNumber: Int) =
            FragmentQuiz().apply {
                arguments = Bundle().apply {
                    putInt(NUMBER_QUESTION, _questonNumber)
                }
            }
//
//        @JvmStatic
//        fun newInstance(_questonNumber: Int): FragmentQuiz {
//            val fragment = FragmentQuiz()
//            val args = Bundle()
//            args.putInt(NUMBER_QUESTION, _questonNumber)
//            fragment.arguments = args
//            return fragment
//        }


    }

}

interface ShowFragmentQuiz {
    fun showFragmentQuiz(questonNumber: Int)
}