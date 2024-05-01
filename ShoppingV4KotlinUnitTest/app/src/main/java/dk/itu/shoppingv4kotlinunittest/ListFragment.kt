package dk.itu.shoppingv4kotlinunittest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Observable
import java.util.Observer

class ListFragment : Fragment(), Observer {
    private lateinit var itemsDB: ItemsDB
    private lateinit var listThings: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsDB = ItemsDB.get()
        itemsDB.addObserver(this)
    }

    override fun update(observable: Observable?, data: Any?) {
        listThings.text = "Shopping List" + itemsDB.listItems()
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