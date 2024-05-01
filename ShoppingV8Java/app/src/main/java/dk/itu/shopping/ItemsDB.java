package dk.itu.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ItemsDB {
    private final static String ShoppingURL = "https://shoppingserver.onrender.com/";
    public static final String INITIALS = "BV";
    private List<Item> values;
    private final Semaphore init = new Semaphore(0);

    public ItemsDB() {
        values = new ArrayList<>();
        networkDB(ShoppingURL, "", values);  //This will fetch all items and insert them into values
    }

    // Called from ShoppingActivity
    public void awaitInit() {
        if (values.size() == 0)  //******BV To prevent ShoppingActivity from waiting when onCreate is called because of life-cycle changes
            try {
                init.acquire();
            } catch (InterruptedException ie) {
            }
    }

    private void networkDB(String url, String command, List<Item> values) {
        Runnable r = new HttpThread(url + command, values, init);
        new Thread(r).start();
    }

    public synchronized void addItem(String what, String where) {
        values.add(new Item(what, where));
        networkDB(ShoppingURL, "?op=insert&who=" + INITIALS + "&what=" + what + "&whereC=" + where, values);
    }

    public synchronized void removeItem(String what) {
        // Should delete all rows in values similarly to what happens in database
        for (Item t : values) {
            if (t.getWhat().equals(what)) {
                values.remove(t);
                networkDB(ShoppingURL, "?op=remove&who=" + INITIALS + "&what=" + what, values);
                break;
            }
        }
    }

    public synchronized int size() {
        return values.size();
    }

    public synchronized List<Item> getValues() {
        return values;
    }
}