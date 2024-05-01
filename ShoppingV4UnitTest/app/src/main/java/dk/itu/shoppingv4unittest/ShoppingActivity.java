package dk.itu.shoppingv4unittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class ShoppingActivity extends AppCompatActivity {
    //Shopping V4 using Fragments and landscape orientation

    //Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping);
        itemsDB = ItemsDB.get();
        setUpFragments();
    }

    private void setUpFragments() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentUI = fm.findFragmentById(R.id.container_ui);
        Fragment fragmentList = fm.findFragmentById(R.id.container_list);
        if ((fragmentUI == null) && (fragmentList == null)) {
            fragmentUI = new UIFragment();
            fragmentList = new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_ui, fragmentUI)
                    .add(R.id.container_list, fragmentList)
                    .commit();
        }
    }
}