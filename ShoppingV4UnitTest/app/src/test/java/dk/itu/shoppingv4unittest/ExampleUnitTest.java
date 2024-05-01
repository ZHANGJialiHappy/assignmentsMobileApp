package dk.itu.shoppingv4unittest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ItemsDB itemsDB;
    @Test
    public void ItemsDBtest(){
        //Testing ItemsDB
        itemsDB= ItemsDB.get();

        //Database after fill
        assertEquals(itemsDB.getWhere("coffee"), "Irma");
        assertEquals(itemsDB.size(), 5);

        itemsDB.addItem("x", "y");
        assertEquals(itemsDB.size(), 6);
        assertEquals(itemsDB.getWhere("x"), "y");

        //testing removeItem
        itemsDB.removeItem("coffee");
        assertEquals(itemsDB.size(), 5);

    }
}
