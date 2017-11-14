package lt.birziska.kartuves;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity {
    private String currentWord;
    private List<String> guessedLetters;
    private boolean gameEnded;
    private LinearLayout gameLayout;
    private int incorrectLetter;
    private int correctLetter;

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

        View textInputLayout = findViewById(R.id.text_input_layout);
        textInputLayout.setVisibility(View.VISIBLE);
        View submitLetterButton = findViewById(R.id.submit_letter_button);
        submitLetterButton.setVisibility(View.VISIBLE);
        TextView guessedTextView = (TextView) findViewById(R.id.guessed_letters_text_view);
        guessedTextView.setVisibility(View.VISIBLE);

        // clears all existing crates in the grid
        gameLayout = (LinearLayout) findViewById(R.id.game_layout);
        gameLayout.removeAllViews();
        generatePuzzle(gameLayout);

        UpdateGuessedLetters("");
        incorrectLetter = 0;
        correctLetter = 0;
        gameEnded = false;
    }

    /*
  Initializes the game.
   */
    private void initGameWord() {
        WordsList list  = new WordsList();
        currentWord = list.GetGamesWord();
        guessedLetters = new ArrayList<>();
    }

    private void generatePuzzle(LinearLayout layout) {
        initGameWord();
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
        editText.setText("");
        //close keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if(guessLetterString == null || guessLetterString.isEmpty())
        {
            return;
        }

        UpdateGuessedLetters(guessLetterString);
        char guessLetter = guessLetterString.toLowerCase().charAt(0);
        String currentWordLowerCase = currentWord.toLowerCase();
        boolean wordContainsLetter = currentWordLowerCase.contains(guessLetterString);
        if(wordContainsLetter)
        {
            for( int i = 0; i < currentWordLowerCase.length(); i++ )
            {
                if(guessLetter == currentWordLowerCase.charAt(i))
                {
                    TextView textView = (TextView) gameLayout.getChildAt(i);
                    textView.setText(guessLetterString);
                    correctLetter++;
                    if(correctLetter >= currentWordLowerCase.length())
                    {
                        setGameWon();
                    }
                }
            }
        }
        else
        {
            incorrectLetter ++;
            //show another image
            if(incorrectLetter > 9)
            {
                //show current word
                for( int i = 0; i < currentWordLowerCase.length(); i++ )
                {
                    if('_' == currentWordLowerCase.charAt(i))
                    {
                        TextView textView = (TextView) gameLayout.getChildAt(i);
                        textView.setText(guessLetterString);
                        correctLetter++;
                        if(correctLetter >= currentWordLowerCase.length())
                        {
                            setGameWon();
                        }
                    }
                }

                setGameLost();
            }

        }
    }

    /*
    Updates guessed letters
    Add string to guessedLetters. And updates textView text.
    */
    private void UpdateGuessedLetters(String guessedLetter) {
        if(guessedLetter.isEmpty() == false) {
            guessedLetters.add(guessedLetter);
        }
        TextView guessedTextView = (TextView) findViewById(R.id.guessed_letters_text_view);
        guessedTextView.setText( "Guessed letters: " + TextUtils.join(", ", guessedLetters));
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

        View textInputLayout = findViewById(R.id.text_input_layout);
        textInputLayout.setVisibility(View.INVISIBLE);
        View submitLetterButton = findViewById(R.id.submit_letter_button);
        submitLetterButton.setVisibility(View.INVISIBLE);
        TextView guessedTextView = (TextView) findViewById(R.id.guessed_letters_text_view);
        guessedTextView.setVisibility(View.INVISIBLE);

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

        View textInputLayout = findViewById(R.id.text_input_layout);
        textInputLayout.setVisibility(View.INVISIBLE);
        View submitLetterButton = findViewById(R.id.submit_letter_button);
        submitLetterButton.setVisibility(View.INVISIBLE);
        TextView guessedTextView = (TextView) findViewById(R.id.guessed_letters_text_view);
        guessedTextView.setVisibility(View.INVISIBLE);

        gameEnded = true;
    }
}
