package dk.itu.shopping;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UIFragment extends Fragment {
    //GUI variables
    Button addItem, listItems;
    private TextView newWhat, newWhere;

    // Model: Database of items
    ShoppingViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);

        //Text Fields
        newWhat = v.findViewById(R.id.what_text);
        newWhere = v.findViewById(R.id.where_text);

        //Buttons
        listItems = v.findViewById(R.id.list);
        addItem = v.findViewById(R.id.add_button);

        viewModel = new ViewModelProvider(requireActivity()).get(ShoppingViewModel.class);

        //Only show List button in Portraitmode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listItems.setOnClickListener(view ->
                    getActivity().
                            getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_fragment,
                                    new ListFragment()).commit());
        }

        // adding a new thing
        addItem.setOnClickListener(view -> viewModel.onAddItemClick(newWhat, newWhere, getActivity()));

        return v;
    }
}
