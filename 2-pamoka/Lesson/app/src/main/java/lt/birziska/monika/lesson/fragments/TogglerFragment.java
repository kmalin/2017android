package lt.birziska.monika.lesson.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import lt.birziska.monika.lesson.R;

public class TogglerFragment extends Fragment {

    Switch imageSwitch;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.toggler_fragment, container, false);

        //find elements
        imageSwitch = (Switch) rootView.findViewById(R.id.imageSwitch);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        //hook events
        imageSwitch.setOnCheckedChangeListener(new OnCheckedChange());

        return rootView;
    }

    class OnCheckedChange implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // do something, the isChecked will be
            // true if the switch is in the On position

            // to change visibility call a method: imageView.setVisibility

            // visible state is represented by View.VISIBLE
            // invisible state is represented by View.INVISIBLE

        }
    }
}
