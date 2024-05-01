package dk.itu.shoppingv4kotlinunittest

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var itemsDB: ItemsDB? = null

    @Test
    fun ItemsDBtest() {
        //Testing ItemsDB
        itemsDB = ItemsDB.get()

        //Database after fill
        assertEquals(itemsDB?.getWhere("coffee"), "Irma")
        assertEquals(itemsDB?.size(), 5)
        itemsDB?.addItem("x", "y")
        assertEquals(itemsDB?.size(), 6)
        assertEquals(itemsDB?.getWhere("x"), "y")

        //testing removeItem
        itemsDB?.removeItem("coffee")
        assertEquals(itemsDB?.size(), 5)

    }
}