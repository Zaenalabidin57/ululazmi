package com.example.ululazmi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Button
import android.widget.Toast
import com.example.ululazmi.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuizFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val correctAnswer: Int
    )

    private val quizQuestions = listOf(
        QuizQuestion(
            "Who is the first prophet among Ulul Azmi?",
            listOf("Nuh AS", "Ibrahim AS", "Musa AS", "Isa AS"),
            0
        ),
        QuizQuestion(
            "Which prophet was known for building the Ka'bah with his son?",
            listOf("Nuh AS", "Ibrahim AS", "Musa AS", "Muhammad SAW"),
            1
        ),
        QuizQuestion(
            "Which prophet received the Tawrat (Torah)?",
            listOf("Ibrahim AS", "Isa AS", "Musa AS", "Muhammad SAW"),
            2
        ),
        QuizQuestion(
            "Who was the prophet sent to Bani Israel with the Injil (Gospel)?",
            listOf("Musa AS", "Ibrahim AS", "Muhammad SAW", "Isa AS"),
            3
        ),
        QuizQuestion(
            "Who is the final prophet among Ulul Azmi?",
            listOf("Isa AS", "Musa AS", "Muhammad SAW", "Ibrahim AS"),
            2
        )
    )

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var scoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionTextView = view.findViewById(R.id.questionTextView)
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup)
        submitButton = view.findViewById(R.id.submitButton)
        scoreTextView = view.findViewById(R.id.scoreTextView)

        displayQuestion()

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        val currentQuestion = quizQuestions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question
        optionsRadioGroup.removeAllViews()
        currentQuestion.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(requireContext())
            radioButton.id = index
            radioButton.text = option
            optionsRadioGroup.addView(radioButton)
        }

        scoreTextView.text = "Hasil: $score/${quizQuestions.size}"
    }

    private fun checkAnswer() {
        val selectedId = optionsRadioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, "Silahkan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }
        val currentQuestion = quizQuestions[currentQuestionIndex]
        if (selectedId == currentQuestion.correctAnswer) {
            score++
            Toast.makeText(context, "Benar", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                context,
                "Salah, jawaban yang benar adalah: ${currentQuestion.options[currentQuestion.correctAnswer]}",
                Toast.LENGTH_SHORT
            ).show()
        }

        currentQuestionIndex++
        if (currentQuestionIndex < quizQuestions.size) {
            displayQuestion()
            optionsRadioGroup.clearCheck()
        } else {
            showFinalScore()
        }
    }

    private fun showFinalScore() {
        optionsRadioGroup.visibility = View.GONE
        submitButton.visibility = View.GONE
        questionTextView.text = "Quiz Complete!\nFinal Score: $score/${quizQuestions.size}"

        val percentage = (score.toFloat() / quizQuestions.size) * 100
        when {
            percentage >= 80 -> Toast.makeText(context, "Excellent! MasyaAllah!", Toast.LENGTH_LONG).show()
            percentage >= 60 -> Toast.makeText(context, "Good job! Keep learning!", Toast.LENGTH_LONG).show()
            else -> Toast.makeText(context, "Keep studying! You can do better!", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}