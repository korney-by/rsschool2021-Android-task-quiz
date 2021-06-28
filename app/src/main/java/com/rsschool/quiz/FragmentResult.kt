package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.rsschool.quiz.data.AnswersList
import com.rsschool.quiz.data.QuestionsList
import com.rsschool.quiz.data.Themes
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.interfaces.Navigator
import com.rsschool.quiz.interfaces.Painter
import java.lang.StringBuilder
import kotlin.system.exitProcess

private const val RESULT_PERCENT = "RESULT_PERCENT"

class FragmentResult : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private var resultPercent: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            resultPercent = it.getInt(RESULT_PERCENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Painter().applyTheme(Themes.NO_THEME)
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.closeButton.setOnClickListener { onCloseButton() }
        binding.toStartButton.setOnClickListener { onToStartButton() }
        binding.shareResultButton.setOnClickListener { onShareResultButton() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultPercent?.let { showResult() }
    }

    private fun onShareResultButton() {
        val emailIntent = Intent(Intent.ACTION_SEND)
        with(emailIntent) {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.quiz_results))
            putExtra(Intent.EXTRA_TEXT, getTextResults())
        }
        startActivity(Intent.createChooser(emailIntent, getString(R.string.complete_action_using)))
    }

    private fun getTextResults(): String {

        val result = StringBuilder(String.format(getString(R.string.your_result_send),resultPercent))
        for (i in 0 until QuestionsList.getSize()) {
            result.appendLine("${i + 1}) ${QuestionsList.getText(i)}")
            result.appendLine(getString(R.string.your_answer, QuestionsList.getAnswers(i)[AnswersList.getAnswer(i)]))
        }
        return result.toString()
    }

    private fun showResult() {
        binding.textResult.text = String.format(getString(R.string.your_result), resultPercent)
    }

    private fun onCloseButton() {
        exitProcess(0)
    }

    private fun onToStartButton() {
        Navigator().restartQuiz()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    companion object {
        @JvmStatic
        fun newInstance(resultPercent: Int) =
            FragmentResult().apply {
//                arguments = Bundle().apply {
//                    putInt(RESULT_PERCENT, resultPercent)
//                }
                arguments = bundleOf (
                    RESULT_PERCENT to resultPercent
                )

            }
    }
}

