package com.rsschool.quiz


import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.adapters.PagerAdapterQuiz
import com.rsschool.quiz.adapters.PagerAdapterResult
import com.rsschool.quiz.data.AnswersList
import com.rsschool.quiz.data.QuestionsList
import com.rsschool.quiz.data.Themes
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.dialogs.DialogCloseApp
import com.rsschool.quiz.interfaces.Navigator
import com.rsschool.quiz.interfaces.Painter
import kotlin.math.min

class MainActivity : AppCompatActivity(), Painter, Navigator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PagerAdapterQuiz
    private var isResultShow = false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(RESULT_SHOW, isResultShow)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isResultShow = savedInstanceState.getBoolean(RESULT_SHOW)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (isResultShow) {
                    setColorThemeAndStatusBar(Themes.NO_THEME)
                } else {
                    setColorThemeAndStatusBar(position)
                }
            }

        })
    }

    override fun onStart() {
        super.onStart()
        startApp()
    }

    private fun setupPagerAdapterQuiz() {
        adapter = PagerAdapterQuiz(this)
        binding.viewPager2.adapter = adapter
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun setupPagerAdapterResult() {
        binding.viewPager2.adapter = PagerAdapterResult(this)
    }

    private fun openFragmentQuiz(questionNumber: Int) {
        binding.viewPager2.currentItem = questionNumber
    }

    private fun setColorThemeAndStatusBar(number: Int) {
        val numberTheme = if (number < QuestionsList.getSize()) number else Themes.NO_THEME
        setTheme(Themes.getTheme(numberTheme))
        setColorStatusBar()
    }

    private fun setColorStatusBar() {
        val typedValue = TypedValue()
        theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
        window.statusBarColor = typedValue.data
    }

    private fun startApp() {
        if (!isResultShow) {
            setupPagerAdapterQuiz()
            openFragmentQuiz(0)
        } else {
            setupPagerAdapterResult()
        }

    }

    override fun showResult() {
        isResultShow = true
        startApp()
    }

    override fun restartQuiz() {
        AnswersList.revokeAll()
        isResultShow = false
        startApp()
    }

    override fun applyTheme(number: Int) {
        setTheme(Themes.getTheme(number))
    }

    override fun showFragmentQuiz(questionNumber: Int) {
        openFragmentQuiz(questionNumber)
    }

    override fun getFragmentResult(): FragmentResult {
        return FragmentResult.newInstance(QuestionsList.getResult(AnswersList))
    }

    override fun getFragmentQuiz(position: Int): FragmentQuiz {
        return FragmentQuiz.newInstance(position)
    }

    override fun onSetNewAnswer(position: Int) {
        adapter.notifyItemInserted(position + 1)
    }

    override fun onBackPressed() {
        val dialog = DialogCloseApp()
        val manager = supportFragmentManager
        dialog.show(manager, "Exit")

    }

    override fun getCountPagesQuiz(): Int {
        return min(AnswersList.getHasAnswers() + 1, QuestionsList.getSize())
    }

    companion object {
        private const val RESULT_SHOW = "RESULT_SHOW"
    }

}