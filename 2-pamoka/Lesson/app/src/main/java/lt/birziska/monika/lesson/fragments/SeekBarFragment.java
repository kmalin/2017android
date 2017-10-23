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

        //initial kitten alpha
        kittenImage.setImageAlpha(0);

        //set seekbar
        seekBar = (SeekBar)rootView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBarChange());
        return rootView;
    }

    private class SeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //get seekBarProgress
           Integer seekBarProgress = seekBar.getProgress();

            //bellow set image alpha(opacity) use .setImageAlpha function
            //set here kitten alpha should be seekBarProgress value

            //set here batman alpha should be the difference of seekBarProgress and max value of seekBar user function .getMax()
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }
}
