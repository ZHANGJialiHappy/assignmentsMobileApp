package dk.itu.gabage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsDB = new ItemsDB();
        itemsDB.fillItemsDB();
        itemsDB.addItem("haha", "hi");

        EditText input = findViewById(R.id.editItems);

        Button button = findViewById(R.id.where_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = input.getText().toString().trim().toLowerCase();
                for (Item i : itemsDB.getItemsDB()) {
                    if (i.getWhat().equals(name)) {
                        input.setText(i.toString());
                        return;
                    }
                }
                input.setText(name + " should be placed in: not found");
            }

        });


    }
}