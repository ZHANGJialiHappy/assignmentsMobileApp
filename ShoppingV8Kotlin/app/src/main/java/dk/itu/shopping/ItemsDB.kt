package dk.itu.shopping

import java.util.concurrent.Semaphore

class ItemsDB {
    private val ShoppingURL = "https://shoppingserver.onrender.com/"
    private val INITIALS = "BV"
    private val values: ArrayList<Item> = ArrayList()
    private val init = Semaphore(0)

    init {
        networkDB(
            ShoppingURL,
            "",
            values,
            INITIALS
        ) //This will fetch all items and insert them into values
    }

    private fun networkDB(url: String, command: String, values: ArrayList<Item>, INITIALS: String) {
        val r: Runnable = HttpThread(url + command, values, INITIALS, init)
        Thread(r).start()
    }

    // Called from ShoppingActivity
    fun awaitInit() {
        if (values.size == 0) //******BV To prevent ShoppingActivity from waiting when onCreate is called because of life-cycle changes
            try {
                init.acquire()
            } catch (ie: InterruptedException) {
            }
    }

    //Used in observer
    @Synchronized
    fun getValues(): ArrayList<Item> {
        return values
    }

    @Synchronized
    fun addItem(what: String, where: String) {
        // adding both in local copy and on server
        values.add(Item(what, where))
        networkDB(
            ShoppingURL,
            "?op=insert&who=" + INITIALS + "&what=" + what + "&whereC=" + where,
            values,
            INITIALS
        )
    }

    @Synchronized
    fun removeItem(what: String) {
        // Should delete all rows in values similarly to what happens in database
        for (t in values) {
            if (t.what == what) {
                // deleting both in local copy and on server
                values.remove(t)
                networkDB(
                    ShoppingURL,
                    "?op=remove&who=" + INITIALS + "&what=" + what,
                    values,
                    INITIALS
                )
                break
            }
        }
    }

    @Synchronized
    fun size(): Int {
        return getValues().size
    }
}