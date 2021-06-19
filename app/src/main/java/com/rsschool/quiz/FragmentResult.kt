package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.quiz.databinding.FragmentResultBinding
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
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.closeButton.setOnClickListener { onCloseButton() }
        binding.toStartButton.setOnClickListener { onToStartButton() }
        binding.shareResultButton.setOnClickListener { onShareResultButton() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultPercent?.let { showResult(it) }
    }

    private fun onShareResultButton() {
        val emailIntent = Intent(Intent.ACTION_SEND)
        with(emailIntent) {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Quiz results")
            putExtra(Intent.EXTRA_TEXT, getTextResults())
        }
        startActivity(Intent.createChooser(emailIntent,"Complete action using"))
    }

    private fun getTextResults(): String {
        val result = StringBuilder("You result: $resultPercent %\n\n")
        for (i in 0 until QuestionsList.getSize()) {
            result.appendLine("${i+1}) ${QuestionsList.getQuestion(i).text}")
            result.appendLine("Your answer: ${QuestionsList.getQuestion(i).answers[AnswersList.getAnswer(i)]}\n")
        }
        return result.toString()
    }

    private fun showResult(resultPercent: Int) {
        binding.textResult.text = String.format(getString(R.string.your_result), resultPercent)
    }

    private fun onCloseButton() {
        exitProcess(0)
    }

    private fun onToStartButton() {
        repeatQuiz()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun repeatQuiz() {
        AnswersList.revokeAll()
        Navigator().showFragmentQuiz(0)
    }

    companion object {
        @JvmStatic
        fun newInstance(resultPercent: Int) =
            FragmentResult().apply {
                arguments = Bundle().apply {
                    putInt(RESULT_PERCENT, resultPercent)
                }
            }
    }
}

