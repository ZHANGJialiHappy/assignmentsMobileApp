package dk.itu.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

public class ShoppingActivity extends AppCompatActivity {
    //Shopping V8 using SQLite database

    private FragmentManager fm;
    Fragment fragmentUI, fragmentList;

    // Model: Database of items
    private ShoppingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingmain);

        viewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);
        // Context is needed to set up SQLite
        viewModel.initializeDB(this);

        fm = getSupportFragmentManager();
        setUpFragments();
    }

    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentUI = fm.findFragmentById(R.id.container_ui_landscape);
            fragmentList = fm.findFragmentById(R.id.container_list);
            if ((fragmentUI == null) && (fragmentList == null)) {
                Fragment fragmentUI = new UIFragment();
                Fragment fragmentList = new ListFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_landscape, fragmentUI)
                        .add(R.id.container_list, fragmentList)
                        .commit();
            }
        } else {
            //Orientation portrait
            if (fragmentUI == null) {
                fragmentUI = new UIFragment();
                fm.beginTransaction()
                        .add(R.id.main_fragment, fragmentUI)
                        .commit();
            }
        }
    }
}