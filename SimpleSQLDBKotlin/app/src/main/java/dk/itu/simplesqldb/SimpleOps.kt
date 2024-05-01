package dk.itu.simplesqldb

import android.database.sqlite.SQLiteDatabase
import android.widget.TextView

fun SimpleOps(db: SQLiteDatabase, disp: TextView) {
    db.execSQL("INSERT INTO Items (what, whereC) VALUES ('coffee', 'irma')")
    disp.append("\ncoffee, irma added")

    db.execSQL("INSERT INTO Items (what, whereC) VALUES ('milk', 'netto')")
    disp.append("\nmilk, netto added")

    val cursor = db.query("Items", null, null, null, null, null, null)
    cursor.moveToFirst()
    while (!cursor.isAfterLast) {
        disp.append("\nCursor: " + cursor.getString(0) + " " + cursor.getString(1))
        cursor.moveToNext()
    }
    cursor.close()
}