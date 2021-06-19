package com.rsschool.quiz

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.databinding.FragmentQuizBinding


private const val NUMBER_QUESTION = "NUMBER_QUESTION"

class FragmentQuiz : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var questionNumber = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //setTheme()

        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        binding.nextButton.setOnClickListener { onNextButton() }
        binding.previousButton.setOnClickListener { onPreviousButton() }
        binding.radioGroupAnswers.setOnCheckedChangeListener { _, checkedId ->
            onRadioGroupAnswers(
                checkedId
            )
        }
        binding.quizToolbar.setNavigationOnClickListener { onQuizToolbar() }

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
    }


    private fun onRadioGroupAnswers(checkedId: Int) {
        saveAnswerState(checkedId)
        setEnabledButtons()
    }

    private fun onNextButton() {
        if (questionNumber < QuestionsList.getSize() - 1) {
            loadQuestion(1)
            showActualData()
        } else {
            openResultFragment()
        }
    }

    private fun onPreviousButton() {
        loadQuestion(-1)
        showActualData()
    }

    private fun onQuizToolbar() {
        binding.previousButton.callOnClick()
    }


    private fun openResultFragment() {
        Navigator().showFragmentResult(getResultQuiz())
    }

    private fun getResultQuiz(): Int {
        var correctCount = 0
        for (i in 0 until QuestionsList.getSize()) {
            if (QuestionsList.getQuestion(i).correctAnswer == AnswersList.getAnswer(i)) {
                correctCount++
            }
        }
        return correctCount * 100 / QuestionsList.getSize()
    }

    private fun saveAnswerState(answer: Int) {
        AnswersList.setAnswer(questionNumber, answer)
    }

    private fun showActualData() {
        setEnabledButtons()
        binding.quizToolbar.title = "Вопрос ${questionNumber + 1}"

    }

    private fun setEnabledButtons() {
        if (questionNumber < 0) return

        binding.nextButton.isEnabled =
            (questionNumber < QuestionsList.getSize()) && !AnswersList.isNoAnswer(questionNumber)
        binding.previousButton.isEnabled = (questionNumber > 0)
    }

//    private fun setTheme() {
//        var resID:Int
//        //resID=
//        context?.theme?.applyStyle("@style/Theme.Quiz.Second".getRe, true);
//
//        setColorStatusBar()
//    }
//
//    private fun changeTheme(position: Int) {
//        setTheme(Themes.getThemeId(position))
//        val typedValue = TypedValue()
//        theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
//        window.statusBarColor = typedValue.data
//    }

//
//    private fun setColorStatusBar() {
//        val typedValue = TypedValue()
//        context?.theme?.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
//        activity?.window?.statusBarColor = typedValue.data
//    }


    private fun loadQuestion(shift: Int = 0) {
        if (questionNumber + shift !in 0 until QuestionsList.getSize()) {
            return
        }
        questionNumber += shift
        val question = QuestionsList.getQuestion(questionNumber)

        binding.question.text = question.text

        binding.radioGroupAnswers.removeAllViews()

        val layoutParams = RadioGroup.LayoutParams(
            RadioGroup.LayoutParams.MATCH_PARENT,
            RadioGroup.LayoutParams.WRAP_CONTENT
        )

        for (i in 0 until question.answers.size) {
            val radioButtonAnswer = MaterialRadioButton(binding.radioGroupAnswers.context)
            val e = question.answers[i]
            with(radioButtonAnswer) {
                text = e
                id = i
                isUseMaterialThemeColors = true
                isChecked = (i == AnswersList.getAnswer(questionNumber))
                setTextAppearance(R.style.radio_button_text_size)
                setLayoutParams(layoutParams)
            }
            binding.radioGroupAnswers.addView(radioButtonAnswer)
        }
        //setTheme()
    }

    companion object {
        @JvmStatic
        fun newInstance(questionNumber: Int) =
            FragmentQuiz().apply {
                arguments = Bundle().apply {
                    putInt(NUMBER_QUESTION, questionNumber)
                }
            }
//
//        @JvmStatic
//        fun newInstance(questionNumber: Int): FragmentQuiz {
//            val fragment = FragmentQuiz()
//            val args = Bundle()
//            args.putInt(NUMBER_QUESTION, questionNumber)
//            fragment.arguments = args
//            return fragment
//        }


    }

}
