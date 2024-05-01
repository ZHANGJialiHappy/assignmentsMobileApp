package dk.itu.shopping

import java.lang.Runnable
import java.util.concurrent.Semaphore

class HttpThread(// to pass parameters to thread
    var url: String, var values: ArrayList<Item>, var INITIALS: String, var init: Semaphore
) : Runnable {

    // doInBackGround
    override fun run() {
        NetworkFetcher().fetchItems(url, values, INITIALS)
        init.release() //Signals that initialization of values is finished
    }
}