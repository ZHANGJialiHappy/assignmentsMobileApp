package dk.itu.shoppingv4

class ItemsDB private constructor() {
    private val itemsDB = HashMap<String, String>()

    init {
        fillItemsDB()
    }

    companion object {
        private var sItemsDB: ItemsDB? = null

        fun get(): ItemsDB {
            return sItemsDB ?: ItemsDB().also{sItemsDB = it}
        }
    }

    fun listItems(): String {
        var r = ""
        for ((key, value) in itemsDB)
            r = "$r\n Buy $key in: $value"
        return r
    }

    fun size(): Int {
        return itemsDB.size
    }

    fun getWhere(what: String): String? {
        return itemsDB[what]
    }

    fun addItem(what: String, where: String) {
        itemsDB[what] = where
    }

    fun removeItem(what: String) {
        if (itemsDB[what] != null) itemsDB.remove(what)
    }

    private fun fillItemsDB() {
        itemsDB["coffee"] = "Irma"
        itemsDB["carrots"] = "Netto"
        itemsDB["milk"] = "Netto"
        itemsDB["bread"] = "bakery"
        itemsDB["butter"] = "Irma"
    }
}