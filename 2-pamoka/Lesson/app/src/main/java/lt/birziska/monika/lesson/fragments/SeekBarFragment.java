package lt.birziska.monika.lesson.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import lt.birziska.monika.lesson.R;

public class SeekBarFragment extends Fragment {

    SeekBar seekBar;
    ImageView batmanImage;
    ImageView kittenImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.seek_bar_fragment, container, false);

        //set images
        batmanImage = (ImageView) rootView.findViewById(R.id.batmanImageView);
        kittenImage = (ImageView) rootView.findViewById(R.id.kittenImageView);

        //initial image alphas
        kittenImage.setImageAlpha(255);
        batmanImage.setImageAlpha(0);

        //set seekbar
        seekBar = (SeekBar)rootView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBarChange());
        return rootView;
    }

    private class SeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //to get maximal value that from the seekBar use a method - getMax()

            //set image alpha(opacity) use .setImageAlpha function

            //set batman alpha, should be seekBarProgress value
            //set kitten alpha, should be the difference of seekBarProgress and max value of seekBar method getMax()
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }
}
