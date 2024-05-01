package dk.itu.shoppinglivedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UIFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);
        final ShoppingViewModel viewModel = new ViewModelProvider(requireActivity()).get(ShoppingViewModel.class);

        //Text Fields
        TextView itemWhat = v.findViewById(R.id.what_text);
        TextView itemWhere = v.findViewById(R.id.where_text);

        Button addItem = v.findViewById(R.id.add_button);
        // adding a new thing
        addItem.setOnClickListener(view -> viewModel.onAddItemClick(itemWhat, itemWhere, getActivity()));

        Button findItems = v.findViewById(R.id.find_button);
        findItems.setOnClickListener(view -> viewModel.onFindItemClick(itemWhat, itemWhere));

        return v;
    }
}

