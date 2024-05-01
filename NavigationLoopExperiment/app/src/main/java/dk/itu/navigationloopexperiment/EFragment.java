package dk.itu.navigationloopexperiment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class EFragment extends Fragment {
    int myColor = Color.WHITE;
    String myText = "Just created!\nI am a new";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_e, container, false);
        v.setBackgroundColor(myColor);

        TextView textView = v.findViewById(R.id.fragment_old_text);
        textView.setText(myText);

        Button nextButton = v.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myColor = Color.CYAN;
                myText = "Welcome back!\n I am an old";
                Navigation.findNavController(view).navigate(R.id.action_EFragment_to_CFragment);
            }
        });
        return v;
    }
}

