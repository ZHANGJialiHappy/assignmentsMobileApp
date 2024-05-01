package dk.itu.gabage;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private List<Item> ItemsDB= new ArrayList<>();

    public ItemsDB() { }

    public List<Item> getItemsDB() {
        return ItemsDB;
    }

    public void addItem(String what, String where){
        ItemsDB.add(new Item(what, where));
    }

    public void fillItemsDB() {
        ItemsDB.add(new Item("milk carton", "Food"));
        ItemsDB.add(new Item("milk carton", "Food"));
    }
}
