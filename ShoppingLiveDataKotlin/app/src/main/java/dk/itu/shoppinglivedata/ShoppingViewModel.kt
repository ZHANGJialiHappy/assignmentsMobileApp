package dk.itu.shoppinglivedata

import android.graphics.Color
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ShoppingViewModel: ViewModel() {

    private val itemsDB: ItemsDB = ItemsDB.get()

    val uiState: MutableLiveData<ShoppingUiState> =
        MutableLiveData<ShoppingUiState>(ShoppingUiState(itemsDB.listItems()))

    fun onAddItemClick(itemWhat: TextView, itemWhere: TextView, activity: FragmentActivity) {
        val whatS = itemWhat.text.toString().trim()
        val whereS = itemWhere.text.toString().trim()
        if (whatS.isNotEmpty() && whereS.isNotEmpty()) {
            itemsDB.addItem(whatS, whereS)
            itemWhat.text = ""
            itemWhere.text = ""
            itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE) //to close the keyboard when done with the text
            itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE)
        } else showToast(activity)
    }

    fun onFindItemClick(itemWhat: TextView, itemWhere: TextView) {
        val what = itemWhat.text.toString().trim()
        itemWhat.setBackgroundColor(Color.parseColor("#FFFFFF"))
        itemWhere.text = itemsDB.getWhere(what)
        itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE) //to close the keyboard when done with the text
        itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    private fun showToast(activity: FragmentActivity) {
        Toast.makeText(activity, R.string.empty_toast, Toast.LENGTH_LONG).show()
    }

    data class ShoppingUiState(
        val listItems: String
    )
}