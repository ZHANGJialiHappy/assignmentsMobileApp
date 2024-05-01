package dk.itu.shoppingwithslowsearchproblem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    private static ItemsDB itemsDB;
    private TextView listThings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        itemsDB= ItemsDB.get();
        listThings= findViewById(R.id.listItems);
        listThings.setText("Shopping List"+itemsDB.listItems());
    }
}
