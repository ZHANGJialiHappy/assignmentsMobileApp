package dk.itu.simplesqldb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val out: TextView = findViewById(R.id.display)
        out.text = "\nStarted"

        mDatabase = SimpleSQL(this).writableDatabase
        out.append("\nItems database created")

        SimpleOps(mDatabase!!, out)
    }

    companion object {
        private var mDatabase: SQLiteDatabase? = null
    }
}