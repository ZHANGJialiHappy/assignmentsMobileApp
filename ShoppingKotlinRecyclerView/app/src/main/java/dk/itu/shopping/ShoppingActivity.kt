package dk.itu.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingActivity : AppCompatActivity() {
    //Shopping with Recyclerview
    // ViewModel
    private lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shoppingmain)

        viewModel = ViewModelProvider(this)[ShoppingViewModel::class.java]

        //Text Fields
        val newWhat = findViewById<EditText>(R.id.what_text)
        val newWhere = findViewById<EditText>(R.id.where_text)
        val itemList = findViewById<RecyclerView>(R.id.listItems)
        itemList.layoutManager = LinearLayoutManager(this)
        val mAdapter = ItemAdapter()
        itemList.adapter = mAdapter
        viewModel.uiState.observe(this) { mAdapter.notifyDataSetChanged() }
        val addItem = findViewById<Button>(R.id.add_button)
        // adding a new thing
        addItem.setOnClickListener {
            viewModel.onAddItemClick(newWhat, newWhere, this)
        }
    }

    private inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mWhatTextView: TextView
        private val mWhereTextView: TextView

        init {
            mWhatTextView = itemView.findViewById(R.id.item_what)
            mWhereTextView = itemView.findViewById(R.id.item_where)
        }

        fun bind(item: Item, position: Int) {
            mWhatTextView.text = item.what
            mWhereTextView.text = item.where
        }
    }

    private inner class ItemAdapter : RecyclerView.Adapter<ItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val layoutInflater = LayoutInflater.from(this@ShoppingActivity)
            val v = layoutInflater.inflate(R.layout.one_row, parent, false)
            return ItemHolder(v)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val item = viewModel.uiState.value!!.itemList[position]
            holder.bind(item, position)
        }

        override fun getItemCount(): Int {
            return viewModel.uiState.value!!.itemListSize
        }
    }
}