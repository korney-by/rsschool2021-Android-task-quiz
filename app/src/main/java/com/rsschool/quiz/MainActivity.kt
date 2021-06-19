package com.rsschool.quiz


import android.os.Bundle
import android.util.TypedValue

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.AnswersList
import com.rsschool.quiz.data.Themes
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.interfaces.Navigator
import com.rsschool.quiz.interfaces.Painter

class MainActivity : AppCompatActivity(), Painter, Navigator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startQuiz()
    }

    private fun openFragmentQuiz(questonNumber: Int) {
        val fragmentQuiz = FragmentQuiz.newInstance(questonNumber)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragmentQuiz)
            .commit()
    }

    private fun openFragmentResult(resultPercent: Int) {
        val fragmentResult: Fragment = FragmentResult.newInstance(resultPercent)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragmentResult)
            .commit()
    }


    private fun setColorTheme(number: Int) {
        setTheme(Themes.getTheme(number))
        setColorStatusBar()
    }

    private fun setColorStatusBar() {
        val typedValue = TypedValue()
        theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
        window.statusBarColor = typedValue.data
    }

    private fun startQuiz(){
        applyTheme(0)
        openFragmentQuiz(0)
    }

    override fun restartQuiz() {
        AnswersList.revokeAll()
        startQuiz()
    }

    override fun applyTheme(number: Int) {
        setColorTheme(number)
    }

    override fun showFragmentQuiz(questionNumber: Int) {
        openFragmentQuiz(questionNumber)
    }

    override fun showFragmentResult(resultPercent: Int) {
        openFragmentResult(resultPercent)
    }

}