package lt.birziska.kartuves;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private String currentWord;
    private boolean gameEnded;
    private LinearLayout gameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button submitLetterButton = (Button) findViewById(R.id.submit_letter_button);
        submitLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLetter();
            }
        });

        Button restartGameButton = (Button) findViewById(R.id.restart_game_button);
        restartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });

        initGame();
    }

    /*
   Initializes the game.
    */
    private void initGame() {
        // hides game over text
        View gameOverLayout = findViewById(R.id.game_over_layout);
        gameOverLayout.setVisibility(View.INVISIBLE);

        // clears all existing crates in the grid
        gameLayout = (LinearLayout) findViewById(R.id.game_layout);
        generatePuzzle(gameLayout);


        gameEnded = false;
    }

    private void generatePuzzle(LinearLayout layout) {
        //gets word from list
        currentWord = "lesson";
        for( int i = 0; i < currentWord.length(); i++ )
        {
            TextView textView = new TextView(this);
            textView.setPadding(0,0,20,0);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(20);
            textView.setText("_");
            layout.addView(textView);
        }
    }

    /*
   Initializes the game.
    */
    private void submitLetter() {
        EditText editText = (EditText) findViewById(R.id.edit_text);
        String guessLetterString = editText.getText().toString();
        char guessLetter = guessLetterString.toLowerCase().charAt(0);
        boolean wordContainsLetter = currentWord.contains(guessLetterString);
        if(wordContainsLetter)
        {
            for( int i = 0; i < currentWord.length(); i++ )
            {
                if(guessLetter == currentWord.charAt(i))
                {
                    TextView textView = (TextView) gameLayout.getChildAt(i);
                    textView.setText(guessLetterString);
                }
            }
        }
    }

    /*
  Sets the game is lost state.
  Shows the game over text. And sets gameEnded flag.
   */
    private void setGameLost() {
        TextView gameOverTextView = (TextView) findViewById(R.id.game_over_text_view);
        gameOverTextView.setText("You lost.");

        View gameOverLayout = findViewById(R.id.game_over_layout);
        gameOverLayout.setVisibility(View.VISIBLE);

        gameEnded = true;
    }

    /*
    Sets the game is won state.
    Shows the game over text. And sets gameEnded flag.
     */
    private void setGameWon() {
        TextView gameOverTextView = (TextView) findViewById(R.id.game_over_text_view);
        gameOverTextView.setText("You won.");

        View gameOverLayout = findViewById(R.id.game_over_layout);
        gameOverLayout.setVisibility(View.VISIBLE);

        gameEnded = true;
    }
}
