package lt.birziska.monika.lesson.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import lt.birziska.monika.lesson.R;

public class ProgressFragment extends Fragment {

    ProgressBar progressBar;
    Button button;
    TextView textView;
    Handler handler = new Handler();

    int progresStatus = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.progress_fragment, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        button = (Button) rootView.findViewById(R.id.progressButton);
        textView = (TextView) rootView.findViewById(R.id.progressTextView);

        button.setOnClickListener(new ProgressButtonClick());
        return rootView;
    }

    class ProgressButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            MagicCodeForProgressBar(1, 100);
        }
    }

    private void MagicCodeForProgressBar(final int incrementor, final long speed) {
        progresStatus = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progresStatus < progressBar.getMax()){
                    progresStatus = progresStatus + incrementor;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progresStatus);
                            textView.setText("Progress " + progresStatus + "/" + progressBar.getMax());
                        }
                    });
                    try{
                        Thread.sleep(speed);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

