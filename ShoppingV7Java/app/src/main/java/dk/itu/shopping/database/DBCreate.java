package dk.itu.shopping.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dk.itu.shopping.database.ItemsDbSchema.ItemTable;

public class DBCreate extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "shopping.db";

    public DBCreate(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.NAME + "(" +
                ItemTable.Cols.WHAT + ", " + ItemTable.Cols.WHERE + ")"
        );

        //For testing purposes add some items to the database
        addItem(db, "coffee", "Irma");
        addItem(db, "carrots", "Netto");
        addItem(db, "milk", "Netto");
        addItem(db, "bread", "bakery");
        addItem(db, "butter", "Irma");
    }

    private void addItem(SQLiteDatabase db, String what, String where) {
        String sql = "INSERT INTO Items (what, whereC) VALUES (?, ?)";
        db.execSQL(sql, new Object[]{what, where});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
