package dk.itu.navigationexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class AFragment extends Fragment {
    Button nextButtonToB;
    Button nextButtonToC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_a, container, false);

        nextButtonToB = v.findViewById(R.id.next_B_button);
        nextButtonToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_AFragment_to_BFragment);
            }
        });

        nextButtonToC = v.findViewById(R.id.next_C_button);
        nextButtonToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_AFragment_to_CFragment);
            }
        });
        return v;
    }
}

