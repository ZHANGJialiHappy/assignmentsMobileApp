package dk.itu.shoppingviewmodel;

import android.graphics.Color;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

public class ShoppingViewModel extends ViewModel {

    // Model: Database of items
    private static final ItemsDB itemsDB = ItemsDB.get();

    public void onAddItemClick(TextView itemWhat, TextView itemWhere, FragmentActivity activity) {
        String whatS = itemWhat.getText().toString().trim();
        String whereS = itemWhere.getText().toString().trim();
        if ((whatS.length() > 0) && (whereS.length() > 0)) {
            itemsDB.addItem(whatS, whereS);
            itemWhat.setText("");
            itemWhere.setText("");
            itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
            itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
        } else
            showToast(activity);
    }

    public void onFindItemClick(TextView itemWhat, TextView itemWhere) {
        String what= itemWhat.getText().toString().trim();
        itemWhat.setBackgroundColor(Color.parseColor("#FFFFFF"));
        itemWhere.setText(itemsDB.getWhere(what));
        itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
        itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

    private void showToast(FragmentActivity activity) {
        Toast.makeText(activity, R.string.empty_toast, Toast.LENGTH_LONG).show();
    }
}
