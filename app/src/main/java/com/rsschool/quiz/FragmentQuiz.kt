package com.rsschool.quiz


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.data.AnswersList
import com.rsschool.quiz.data.QuestionsList
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.interfaces.Navigator
import com.rsschool.quiz.interfaces.Painter


private const val NUMBER_QUESTION = "NUMBER_QUESTION"

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

        binding.nextButton.setOnClickListener { onNextButton() }
        binding.previousButton.setOnClickListener { onPreviousButton() }
        binding.radioGroupAnswers.setOnCheckedChangeListener { _, checkedId ->
            onRadioGroupAnswers(checkedId)
        }
        binding.quizToolbar.setNavigationOnClickListener { onQuizToolbar() }

        questionNumber = arguments?.getInt(NUMBER_QUESTION) ?: 0

        loadQuestion()
        showActualData()
    }


    private fun onRadioGroupAnswers(checkedId: Int) {
        saveAnswerState(checkedId)
        setEnabledButtons()
    }

    private fun onNextButton() {
        if (questionNumber < QuestionsList.getSize() - 1) {
            Painter().applyTheme(questionNumber + 1)
            openQuestion(questionNumber + 1)
        } else {
            Painter().applyTheme(-1)
            openResultFragment()
        }
    }

    private fun openQuestion(number: Int) {
        Navigator().showFragmentQuiz(number)
    }

    private fun onPreviousButton() {
        if (questionNumber > 0) {
            Painter().applyTheme(questionNumber - 1)
            openQuestion(questionNumber - 1)
        }
    }

    private fun onQuizToolbar() {
        binding.previousButton.callOnClick()
    }

    private fun openResultFragment() {
        Navigator().showFragmentResult(QuestionsList.getResult(AnswersList))
    }

    private fun saveAnswerState(answer: Int) {
        AnswersList.setAnswer(questionNumber, answer)
    }

    private fun showActualData() {
        setEnabledButtons()
        binding.quizToolbar.title = "Question ${questionNumber + 1}"
        if (questionNumber == QuestionsList.getSize() - 1) {
            binding.nextButton.text = getString(R.string.submit_button)
        }
    }

    private fun setEnabledButtons() {
        if (questionNumber < 0) return

        binding.previousButton.visibility = if (questionNumber == 0) View.GONE else View.VISIBLE
        binding.nextButton.isEnabled =
            (questionNumber < QuestionsList.getSize()) && !AnswersList.isNoAnswer(questionNumber)
        binding.previousButton.isEnabled = (questionNumber > 0)
    }




    private fun loadQuestion() {
        binding.question.text = QuestionsList.getText(questionNumber)
        binding.radioGroupAnswers.removeAllViews()

        val layoutParams = RadioGroup.LayoutParams(
            RadioGroup.LayoutParams.MATCH_PARENT,
            RadioGroup.LayoutParams.WRAP_CONTENT
        )

        for (i in 0 until QuestionsList.getAnswers(questionNumber).size) {
            val radioButtonAnswer = MaterialRadioButton(binding.radioGroupAnswers.context)
            val e = QuestionsList.getAnswers(questionNumber)[i]
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
    }

    companion object {
//        @JvmStatic
//        fun newInstance(questionNumber: Int) =
//            FragmentQuiz().apply {
//                arguments = Bundle().apply {
//                    putInt(NUMBER_QUESTION, questionNumber)
//                }
//            }

        @JvmStatic
        fun newInstance(questionNumber: Int): FragmentQuiz {
            val fragment = FragmentQuiz()
            val args = Bundle().apply {
                putInt(NUMBER_QUESTION, questionNumber)
            }
            fragment.arguments = args
            return fragment
        }


    }

}
