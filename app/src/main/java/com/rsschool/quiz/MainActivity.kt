package com.rsschool.quiz


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)

//            viewPager.adapter=MyAdapter()
//            viewPager.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        }


        openFragmentQuiz(0)

    }

    private fun openFragmentQuiz(questonNumber: Int) {
        val fragmentQuiz = FragmentQuiz.newInstance(questonNumber)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragmentQuiz)
            //.add(R.id.main_container, fragmentQuiz)
            .commit()
    }

    private fun openFragmentResult(resultPercent: Int) {
        val fragmentResult: Fragment = FragmentResult.newInstance(resultPercent)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentResult)
        transaction.commit()
    }

    override fun showFragmentQuiz(questonNumber: Int) {
        openFragmentQuiz(questonNumber)
    }

    override fun showFragmentResult(resultPercent: Int) {
        openFragmentResult(resultPercent)
    }

}