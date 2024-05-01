package dk.itu.shopping

import android.app.Activity
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingViewModel : ViewModel() {

    private val itemsDB: ItemsDB = ItemsDB.get()

    val uiState: MutableLiveData<ShoppingUiState> =
        MutableLiveData<ShoppingUiState>(ShoppingUiState(itemsDB.items, itemsDB.size()))

    fun onAddItemClick(newWhat: TextView, newWhere: TextView, activity: Activity) {
        val whatS = newWhat.text.toString().trim { it <= ' ' }
        val whereS = newWhere.text.toString().trim { it <= ' ' }
        if (whatS.isNotEmpty() && whereS.isNotEmpty()) {
            itemsDB.addItem(whatS, whereS)
            newWhat.text = ""
            newWhere.text = ""
            newWhat.onEditorAction(EditorInfo.IME_ACTION_DONE)
            newWhere.onEditorAction(EditorInfo.IME_ACTION_DONE)
            uiState.value = ShoppingUiState(itemsDB.items, itemsDB.size())
        } else showToast(activity)
    }

    private fun showToast(activity: Activity) {
        Toast.makeText(activity, R.string.empty_toast, Toast.LENGTH_LONG).show()
    }

    data class ShoppingUiState(
        val itemList: ArrayList<Item>,
        val itemListSize: Int
    )
}