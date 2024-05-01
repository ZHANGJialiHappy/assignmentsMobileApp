package dk.itu.shoppingwithslowsearchproblem;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private final List<Item> ItemsDB= new ArrayList<>();

    private ItemsDB()  { fillItemsDB(); }

    public static void initialize() {
        if (sItemsDB == null) {
            sItemsDB = new ItemsDB();
        }
    }

    public static ItemsDB get() {
        if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDB;
    }

    private ItemsDB(Context context)  {  fillItemsDB(); }

    public void addItem(String what, String where){
        ItemsDB.add(new Item(what, where));
    }

    public String listItems() {
        String r= "";
        for(Item i: ItemsDB)
            r= r+"\n Buy " + i.toString();
        return r;
    }

    //this is only a mock-up of a slow search in your data set e.g., over a slow network
    public Item slowSearchItem(String what) {
        Item found= null;
        for (Item t: ItemsDB) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (t.getWhat().equals(what.toLowerCase()) ) {
                found= t; break; }
        }
        return found;
    }

  /*
  public Item searchItem(String what) {
    Item found= null;
    for (Item t: ItemsDB) {
      if (t.getWhat().equals(what.toLowerCase()) ) {
        found= t; break; }
    }
    return found;
  }
  */

    public void fillItemsDB() {
        ItemsDB.add(new Item("coffee", "Irma"));
        ItemsDB.add(new Item("carrots", "Netto"));
        ItemsDB.add(new Item("milk", "Netto"));
        ItemsDB.add(new Item("bread", "bakery"));
        ItemsDB.add(new Item("butter", "Irma"));
        //add many items
        for (int i= 0; i<100; i++) { ItemsDB.add(new Item("food"+i, "Irma"+i));  }
    }
}

