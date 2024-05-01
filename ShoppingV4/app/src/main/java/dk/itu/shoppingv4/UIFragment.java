package dk.itu.shoppingv4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UIFragment extends Fragment {
    // Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);

        //Text Fields
        TextView itemWhat = v.findViewById(R.id.what_text);
        TextView itemWhere = v.findViewById(R.id.where_text);

        Button addItem = v.findViewById(R.id.add_button);
        // adding a new thing
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS = itemWhat.getText().toString().trim();
                String whereS = itemWhere.getText().toString().trim();
                if ((whatS.length() > 0) && (whereS.length() > 0)) {
                    itemsDB.addItem(whatS, whereS);
                    itemWhat.setText("");
                    itemWhere.setText("");
                    itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                    itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
                } else
                    Toast.makeText(getActivity(), R.string.empty_toast, Toast.LENGTH_LONG).show();
            }
        });

        Button findItems = v.findViewById(R.id.find_button);
        findItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what= itemWhat.getText().toString().trim();
                itemWhat.setBackgroundColor(Color.parseColor("#FFFFFF"));
                itemWhere.setText(itemsDB.getWhere(what));
                itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });

        return v;
    }
}

