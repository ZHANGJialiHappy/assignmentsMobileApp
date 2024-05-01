package dk.itu.shopping;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModel;

import dk.itu.shopping.database.DBCreate;
import dk.itu.shopping.database.ItemCursorWrapper;
import dk.itu.shopping.database.ItemsDbSchema;

public class ItemsDB {
    private static SQLiteDatabase mDatabase;

    //creating the database
    public void initialize(Context context) {
        if (mDatabase == null) {
            mDatabase = new DBCreate(context.getApplicationContext()).getWritableDatabase();
        }
    }

    public ArrayList<Item> getValues() {
        ArrayList<Item> items = new ArrayList<Item>();
        ItemCursorWrapper cursor = queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public void addItem(String what, String where) {
        Item newItem = new Item(what, where);
        ContentValues values = getContentValues(newItem);
        mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);
    }

    public void removeItem(String what) {
        Item newItem = new Item(what, "");
        String selection = ItemsDbSchema.ItemTable.Cols.WHAT + " LIKE ?";
        int changed = mDatabase.delete(ItemsDbSchema.ItemTable.NAME, selection, new String[]{newItem.getWhat()});
    }

    public int size() {
        return getValues().size();
    }

    // Database helper methods to convert between Items and database rows
    private static ContentValues getContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItemsDbSchema.ItemTable.Cols.WHAT, item.getWhat());
        values.put(ItemsDbSchema.ItemTable.Cols.WHERE, item.getWhere());
        return values;
    }

    static private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ItemsDbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new ItemCursorWrapper(cursor);
    }
}
