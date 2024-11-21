package com.example.ululazmi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuizActivity : AppCompatActivity() {
    private val questions = listOf(
        Question(
            "Which prophet is known as Khalilullah (Friend of Allah)?",
            listOf("Noah", "Abraham", "Moses", "Jesus"),
            1
        ),
        Question(
            "Who was commanded to build an ark?",
            listOf("Noah", "Abraham", "Moses", "Jesus"),
            0
        ),
        // Add more questions here
    )

    private var currentQuestionIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        setupBottomNavigation()
        displayQuestion()
    }
    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        startActivity(Intent(this@QuizActivity, MainActivity::class.java))
                        true
                    }
                    R.id.navigation_about -> {
                        startActivity(Intent(this@QuizActivity, AboutFragment::class.java))
                        true
                    }
                    else -> true
                }
            }
            selectedItemId = R.id.navigation_quiz
        }
    }
    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        findViewById<TextView>(R.id.questionText).text = question.text

        val answersGroup = findViewById<RadioGroup>(R.id.answersGroup)
        answersGroup.removeAllViews()

        question.answers.forEachIndexed { index, answer ->
            val radioButton = RadioButton(this).apply {
                text = answer
                id = index
            }
            answersGroup.addView(radioButton)
        }

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            checkAnswer()
        }
    }

    private fun checkAnswer() {
        val answersGroup = findViewById<RadioGroup>(R.id.answersGroup)
        val selectedId = answersGroup.checkedRadioButtonId

        if (selectedId == questions[currentQuestionIndex].correctAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show()
        }
    }
}
