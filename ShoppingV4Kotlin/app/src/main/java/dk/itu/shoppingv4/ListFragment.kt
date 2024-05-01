package dk.itu.shoppingv4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class ListFragment : Fragment() {
    private lateinit var itemsDB: ItemsDB
    private lateinit var listThings: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsDB = ItemsDB.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_list, container, false)
        listThings = v.findViewById(R.id.listItems)
        listThings.text = "Shopping List" + itemsDB.listItems()
        return v
    }
}