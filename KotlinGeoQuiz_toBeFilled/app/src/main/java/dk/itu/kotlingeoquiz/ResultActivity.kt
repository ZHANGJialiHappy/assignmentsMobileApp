package dk.itu.kotlingeoquiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity: AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var resultTextView: TextView

    private lateinit var gameDB: GameDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        gameDB = GameDB.get()

        nameTextView = findViewById(R.id.name_text_view)

        //**** your code - Step 2

        //****

        resultTextView = findViewById(R.id.result_text_view)
        if (gameDB.isWin()) {
            resultTextView.setText(R.string.win_msg)
        } else {
            resultTextView.setText(R.string.lose_msg)
        }
    }

}