package lt.birziska.kartuves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class StartGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new StartButtonClick());
    }

    class StartButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            int length = Integer.parseInt(String.valueOf(spinner.getSelectedItem()));

            Intent intent = new Intent(getApplicationContext(), Game.class);
            intent.putExtra(Game.DATA_WORD_LENGTH, length);
            startActivity(intent);
        }
    }
}
