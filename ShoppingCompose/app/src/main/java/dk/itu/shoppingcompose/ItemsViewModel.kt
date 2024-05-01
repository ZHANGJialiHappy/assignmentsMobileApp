package dk.itu.shoppingcompose
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {
  private val _items= MutableList(2) { i -> initItem(i) }.toMutableStateList()
      private val items: List<Item>
      get() = _items

  fun addItem(what: String, where: String) {
      _items.add(Item(what, where))
  }

  fun listItems(): List<Item> {
    return items
  }

  private fun initItem(i: Int): Item {
    if (i==0) return Item("coffee", "Irma")
    if (i==1) return Item("carrots","Netto")
    return Item("","")
  }
}
