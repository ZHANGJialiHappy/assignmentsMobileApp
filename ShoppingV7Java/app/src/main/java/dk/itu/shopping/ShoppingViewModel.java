package dk.itu.shopping;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ShoppingViewModel extends ViewModel {

    private static final ItemsDB itemsDB = new ItemsDB();

    private final MutableLiveData<ShoppingUiState> uiState =
            new MutableLiveData<>(ShoppingUiState.getEmpty());

    public LiveData<ShoppingUiState> getUiState() {
        return uiState;
    }

    public void initializeDB(Context context) {
        itemsDB.initialize(context);
        updateUiState();
    }

    public void onAddItemClick(TextView itemWhat, TextView itemWhere, FragmentActivity activity) {
        String whatS = itemWhat.getText().toString().trim();
        String whereS = itemWhere.getText().toString().trim();
        if ((whatS.length() > 0) && (whereS.length() > 0)) {
            itemsDB.addItem(whatS, whereS);
            itemWhat.setText("");
            itemWhere.setText("");
            itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
            itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
            updateUiState();
        } else
            showToast(activity);
    }

    public void onDeleteItemClick(String what, FragmentActivity activity) {
        String message;
        if (containsItem(itemsDB.getValues(), what)) {
            itemsDB.removeItem(what);
            updateUiState();
            message = "Removed " + what;
        } else {
            message = what + " not found";
        }
        showToast(activity, message);
    }

    private boolean containsItem(ArrayList<Item> itemList, String what) {
        for (Item item : itemList) {
            if (item.getWhat().equals(what)) {
                return true;
            }
        }
        return false;
    }

    private void updateUiState() {
        uiState.setValue(new ShoppingUiState(itemsDB.getValues(), itemsDB.size()));

    }

    private void showToast(FragmentActivity activity) {
        Toast.makeText(activity, R.string.empty_toast, Toast.LENGTH_LONG).show();
    }

    private void showToast(FragmentActivity activity, CharSequence message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static class ShoppingUiState {
        final ArrayList<Item> itemList;
        final int itemListSize;

        public ShoppingUiState(ArrayList<Item> itemList, int itemListSize) {
            this.itemList = itemList;
            this.itemListSize = itemListSize;
        }

        public static ShoppingUiState getEmpty() {
            return new ShoppingUiState(new ArrayList<>(), 0);
        }
    }
}
