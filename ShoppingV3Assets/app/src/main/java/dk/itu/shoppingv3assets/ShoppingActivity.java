package dk.itu.shoppingv3assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingActivity extends AppCompatActivity {
    //Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);

        ItemsDB.setContext(ShoppingActivity.this);
        itemsDB = ItemsDB.get();

        //Text Fields
        TextView newWhat = findViewById(R.id.what_text);
        TextView newWhere = findViewById(R.id.where_text);

        Button listItems = findViewById(R.id.list);
        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        Button addItem = findViewById(R.id.add_button);
        // adding a new thing
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS = newWhat.getText().toString().trim();
                String whereS = newWhere.getText().toString().trim();
                if ((whatS.length() > 0) && (whereS.length() > 0)) {
                    itemsDB.addItem(whatS, whereS);
                    newWhat.setText("");
                    newWhere.setText("");
                } else
                    Toast.makeText(ShoppingActivity.this, R.string.empty_toast, Toast.LENGTH_LONG).show();
            }
        });
    }
}