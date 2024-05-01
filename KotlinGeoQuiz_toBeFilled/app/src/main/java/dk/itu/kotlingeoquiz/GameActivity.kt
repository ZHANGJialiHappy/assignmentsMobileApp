package dk.itu.kotlingeoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameActivity: AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var questionTextView: TextView

    private lateinit var gameDB: GameDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameDB = GameDB.get()
        gameDB.initializeNewGame()

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener{
            updateWithAnswer(true)
        }

        falseButton.setOnClickListener{
            updateWithAnswer(false)
        }

        val questionText = gameDB.getQuestion()
        questionTextView.text = questionText
    }

    private fun updateWithAnswer(userAnswer: Boolean) {
        gameDB.checkAnswer(userAnswer)
        if (gameDB.hasNextQuestion()) {
            updateQuestion()
        } else {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateQuestion() {
        gameDB.nextQuestion()
        val questionText = gameDB.getQuestion()
        questionTextView.text = questionText
    }
}