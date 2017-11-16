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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Game extends AppCompatActivity {
    private String currentWord;
    private List<String> guessedLetters;
    private LinearLayout gameLayout;
    private int incorectLetterCount;
    private int correctLetterCount;
    private final int TRY_COUNT = 8;

    private void submitLetter() {
        //close keybord DO NOT TOUCH!
        closeKeyboard();

        //get text value from R.id.edit_text
        //convert text value to string using tosString() method
        EditText editText = (EditText) findViewById(R.id.edit_text);
        String guessLetterString = editText.getText().toString();

        //if the letter is null or empty
        //*bonus you can also check if multiple letters are inputed
        //*then show error message
        //** double bonus check if letter is alphabetical
        if(guessLetterString == null || guessLetterString.isEmpty())
        {
            return;
        }

        //update the guessed letters
        //use method UpdateGuessedLetter() wih is defined at bottom of page
        UpdateGuessedLetters(guessLetterString);

        //convert the letter to character
        //use GameHelpers.toChar() method
        char guessLetter = GameHelpers.toChar(guessLetterString);

        //check if the word contains the letter
        // use method GameHelpers.wordContainsLetter()
        boolean wordContainsLetter = GameHelpers.wordContainsLetter(guessLetter, currentWord);

        //if word contains the letter
        //using the for loop iterate threw current word length
        //to get the current word lengt use .length() method
        //increase correct letter count
        //check if the game if won
        if(wordContainsLetter)
        {
            for( int i = 0; i < currentWord.length(); i++ )
            {
                if(guessLetter == currentWord.charAt(i))
                {
                    TextView textView = (TextView) gameLayout.getChildAt(i);
                    textView.setText(guessLetterString);
                    correctLetterCount++;
                }
            }

            if(correctLetterCount >= currentWord.length())
            {
                setGameWon();
            }
        }
        //otherwise
        //increase the incorect letter count
        //change the image
        //check if the game is lost
        //display the current word
            //using the for loop iterate threw current word length
            //to get the current word lengt use .length() method
            //check if the current words letter is blank
        else
        {
            incorectLetterCount++;
            //show another image
            ImageView image = (ImageView) findViewById(R.id.imageView);
            image.setImageResource(GameHelpers.getResourceIdByCount(incorectLetterCount));

            if(incorectLetterCount > TRY_COUNT)
            {
                setGameLost();

                //show current word
                for( int i = 0; i < currentWord.length(); i++ )
                {
                    if('_' == currentWord.charAt(i))
                    {
                        TextView textView = (TextView) gameLayout.getChildAt(i);
                        textView.setText(guessLetterString);
                        correctLetterCount++;
                        if(correctLetterCount >= currentWord.length())
                        {
                            setGameWon();
                        }
                    }
                }

            }

        }
    }

    /*================================================================================================================
    DO NOT TOUCH THE CODE!!!!!
    OR THE BOOGIE MAN WILL COME AT NIGHT AND EAT YOUR PARENTS!
    AND YOU WILL NOT GET ANY PRESENTS THIS CHRISTMAS
    BUT YOU CAN LOOK AT IT OF COURSE :)
    ====================================================================================================================*/

    private void closeKeyboard() {
        //close keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    }

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
        incorectLetterCount = 0;
        correctLetterCount = 0;

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(0);
    }

    private void generatePuzzle(LinearLayout layout) {
        currentWord = WordsList.GetGamesWord();
        guessedLetters = new ArrayList<>();
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
}
