package com.rsschool.quiz


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ShowFragmentQuiz {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            //setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar))
        openFragmentQuiz(0)

    }

    private fun openFragmentQuiz(questonNumber: Int) {
        val fragmentQuiz: Fragment = FragmentQuiz.newInstance(questonNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragmentQuiz)
        transaction.commit()
    }


    override fun showFragmentQuiz(questonNumber: Int) {
        openFragmentQuiz(questonNumber)
    }


}