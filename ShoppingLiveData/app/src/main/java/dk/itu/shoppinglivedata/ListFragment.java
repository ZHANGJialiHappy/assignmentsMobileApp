package dk.itu.shoppinglivedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
        final TextView listThings = v.findViewById(R.id.listItems);
        final ShoppingViewModel viewModel = new ViewModelProvider(requireActivity()).get(ShoppingViewModel.class);
        viewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> listThings.setText("Shopping List" + uiState.listItems));
        return v;
    }
}
