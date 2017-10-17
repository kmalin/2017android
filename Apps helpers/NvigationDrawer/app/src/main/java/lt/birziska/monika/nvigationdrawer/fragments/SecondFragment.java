package lt.birziska.monika.nvigationdrawer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lt.birziska.monika.nvigationdrawer.R;

public class SecondFragment extends Fragment {

    public SecondFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        TextView t = (TextView) rootView.findViewById(R.id.someTextView);
        t.setText("Second page!!!");
        return rootView;
    }
}

