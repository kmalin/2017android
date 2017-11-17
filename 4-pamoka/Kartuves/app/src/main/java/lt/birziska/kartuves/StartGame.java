package lt.birziska.kartuves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            Intent intent = new Intent(getApplicationContext(), Game.class);
            startActivity(intent);
        }
    }
}
