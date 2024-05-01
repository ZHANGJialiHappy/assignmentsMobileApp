package dk.itu.kotlingeoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var addNameButton: Button
    private lateinit var nameField: EditText

    private lateinit var gameDB: GameDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        gameDB = GameDB.get()

        nameField = findViewById(R.id.name_text)
        nameField.hint = gameDB.playerName

        addNameButton = findViewById(R.id.register_button)
        addNameButton.setOnClickListener{
            val name = nameField.text.toString().trim()
            if (name.length > 0) {
                gameDB.playerName = name
                finish()
            } else {
                Toast.makeText(this, R.string.empty_toast, Toast.LENGTH_LONG).show();
            }
        }
    }
}