package dk.itu.shoppingv5;

import java.util.HashMap;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private final HashMap<String, String> itemsMap = new HashMap<String, String>();

    private ItemsDB() {
        fillItemsDB();
    }

    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB = new ItemsDB();
        return sItemsDB;
    }

    public HashMap<String, String> getItemsDB() {
        return itemsMap;
    }

    public String listItems() {
        String r = "";
        for (HashMap.Entry<String, String> item : itemsMap.entrySet())
            r = r + "\n Buy " + item.getKey() + " in: " + item.getValue();
        return r;
    }

    public int size() {
        return itemsMap.size();
    }

    public String getWhere(String what) {
        return itemsMap.get(what);
    }

    public void addItem(String what, String where) {
        itemsMap.put(what, where);
    }

    public void removeItem(String what) {
        itemsMap.remove(what);
    }

    public boolean isPresent(String what) {
        return itemsMap.get(what) != null;
    }

    private void fillItemsDB() {
        itemsMap.put("coffee", "Irma");
        itemsMap.put("carrots", "Netto");
        itemsMap.put("milk", "Netto");
        itemsMap.put("bread", "bakery");
        itemsMap.put("butter", "Irma");
    }
}
