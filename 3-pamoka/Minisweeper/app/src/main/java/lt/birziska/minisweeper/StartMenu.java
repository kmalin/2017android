package lt.birziska.minisweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        Button startButton = (Button) findViewById(R.id.startButton);
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
