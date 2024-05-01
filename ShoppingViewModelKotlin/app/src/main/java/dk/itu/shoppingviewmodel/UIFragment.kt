package dk.itu.shoppingviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class UIFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_ui, container, false)
        val viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]

        //Text Fields
        val itemWhat: TextView = v.findViewById(R.id.what_text)
        val itemWhere: TextView = v.findViewById(R.id.where_text)

        //Buttons
        val addItem: Button = v.findViewById(R.id.add_button)
        // adding a new thing
        addItem.setOnClickListener {
            activity?.let { fragmentActivity ->
                viewModel.onAddItemClick(
                    itemWhat,
                    itemWhere,
                    fragmentActivity
                )
            }
        }

        val findItems: Button = v.findViewById(R.id.find_button)
        findItems.setOnClickListener {
            viewModel.onFindItemClick(itemWhat, itemWhere)
        }

        return v
    }
}