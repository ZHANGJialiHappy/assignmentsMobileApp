package dk.itu.kotlingeoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //taking care of data initialization for the game
        GameDB.initialize()
        startButton = findViewById(R.id.start_game_button)

        //**** your code - Step 1

        //****

        //**** your code - Step 2

        //****
    }

}