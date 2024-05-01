package dk.itu.shopping;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private final List<Item> items = new ArrayList<>();

    private ItemsDB() {
        fillItemsDB();
    }

    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB = new ItemsDB();
        return sItemsDB;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(String what, String where) {
        items.add(new Item(what, where));
    }

    public void removeItem(String what) {
        for (Item t : items) {
            if (t.getWhat().equals(what)) {
                items.remove(t);
                break;
            }
        }
    }

    public int size() {
        return items.size();
    }

    private void fillItemsDB() {
        items.add(new Item("coffee", "Irma"));
        items.add(new Item("carrots", "Netto"));
        items.add(new Item("milk", "Netto"));
        items.add(new Item("bread", "bakery"));
        items.add(new Item("butter", "Irma"));
        items.add(new Item("apples", "Netto"));
        items.add(new Item("oranges", "Netto"));
        items.add(new Item("cheese", "Netto"));
        items.add(new Item("juice", "Føtex"));
        items.add(new Item("onions", "Netto"));
        items.add(new Item("potatoes", "Menu"));
        items.add(new Item("chips", "Netto"));
        items.add(new Item("coke", "Liedl"));
        items.add(new Item("soap", "Netto"));
        items.add(new Item("cookies", "Irma"));
        items.add(new Item("ham", "Irma"));
    }
}