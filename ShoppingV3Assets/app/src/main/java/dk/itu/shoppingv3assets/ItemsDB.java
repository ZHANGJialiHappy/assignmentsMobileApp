package dk.itu.shoppingv3assets;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private static Context context;
    private final HashMap<String, String> itemsMap= new HashMap<String, String>();

    private ItemsDB() {
        if (context == null)
            throw new IllegalStateException("context must be set first: use setContext(..)");
        fillItemsDB(context,"items.txt");
    }

    public static void setContext(Context aContext) {
        context = aContext;
    }

    public static ItemsDB get() {
        if (sItemsDB == null) {
            sItemsDB = new ItemsDB();
        }
        return sItemsDB;
    }

    public HashMap<String, String> getItemsDB() {return itemsMap; }

    public String listItems() {
        String r= "";
        for (HashMap.Entry <String, String> item: itemsMap.entrySet())
            r= r+"\n Buy "+item.getKey() + " in: "  + item.getValue();
        return r;
    }

    public int size() { return itemsMap.size(); }
    public String getWhere(String what) {return itemsMap.get(what); }

    public void addItem(String what, String where){
        itemsMap.put(what, where);
    }

    public void removeItem(String what){
        if (itemsMap.get(what) != null)  itemsMap.remove(what);
    }

    public void fillItemsDB(Context context, String filename) {
        try {
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
            String line= reader.readLine();
            while (line != null) {
                String[] gItem= line.split(",");
                itemsMap.put(gItem[0], gItem[1]);
                line= reader.readLine();
            }
        } catch (IOException e) {  // Error occurred when opening raw file for reading.
        }
    }
}
