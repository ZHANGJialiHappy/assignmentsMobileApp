package dk.itu.shoppingwithslowsearchproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShoppingActivity extends AppCompatActivity {
    //Shopping V2

    //GUI variables
    private Button addItem, listItems, searchItem;
    private TextView newWhat, newWhere;

    //Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);
        ItemsDB.initialize();
        itemsDB= ItemsDB.get();

        //Text Fields
        newWhat=  findViewById(R.id.what_text);
        newWhere= findViewById(R.id.where_text);

        listItems= findViewById(R.id.list);
        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ShoppingActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        searchItem= findViewById(R.id.search);
        searchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS= newWhat.getText().toString().trim();
                searchItem(whatS);
            }
        });
    }

    private void searchItem(String what) {
        Item found= itemsDB.slowSearchItem(what);
        newWhere.setText(
                (found!=null) ? found.getWhere() : "not found");
    }

}
