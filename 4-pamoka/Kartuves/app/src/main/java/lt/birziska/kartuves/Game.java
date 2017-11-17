package lt.birziska.kartuves;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    private LinearLayout gameLayout;
    private WordList wordList;
    private GameModel gameModel;

    private void submitLetter() {
        //close keybord
        closeKeyboard();

        //get text value from R.id.edit_text
        EditText editText = (EditText) findViewById(R.id.edit_text);
        Editable text = editText.getText();
        if (text.length() == 0){
            return;
        }
        //extract a Character from the text
        Character letter = text.charAt(0);
        letter = Character.toLowerCase(letter);
        //clear the input field
        editText.setText("");

        //main action goes here
        gameModel.submitLetter(letter);

        //after game model is updated, then we need to display a its new state
        updateGameState();
    }

    /*
    Updates the game state from the game model
     */
    private void updateGameState() {

        displayOpenedWord();
        displayIncorrectlyGuessedLetters();
        displayHangman();

        if (gameModel.getGameEnded()){
            if (gameModel.getGameWon()){
                setGameWon();
            }
            else {
                setGameLost();
            }
        }
    }

    private void displayHangman() {
        int incorrectlyGuessedLetterCount = gameModel.getIncorrectlyGuessedLetterCount();

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(GameHelpers.getResourceIdByCount(incorrectlyGuessedLetterCount));
    }

    private void displayIncorrectlyGuessedLetters() {
        Character[] incorrectlyGuessedLetters = gameModel.getIncorrectlyGuessedLetters();
        String joinedLetters = TextUtils.join(", ", incorrectlyGuessedLetters).toUpperCase();

        TextView guessedTextView = (TextView) findViewById(R.id.guessed_letters_text_view);
        guessedTextView.setText( "Guessed letters: " + joinedLetters);
    }

    private void displayOpenedWord() {
        Character[] openedWord = gameModel.getCurrentlyOpenedWord();

        for(int i = 0; i < openedWord.length; i++){
            TextView textView = (TextView) gameLayout.getChildAt(i);
            textView.setText(openedWord[i].toString().toUpperCase());
        }
    }

    private void closeKeyboard() {
        //close keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

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

        wordList = new WordList(getAssets());
        gameModel = new GameModel(wordList, 5);

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
                initializeGame();
            }
        });

        initializeGame();
    }

    private void initializeGame() {
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

        gameModel.initializeGame();

        generatePuzzle(gameLayout);

        //ImageView image = (ImageView) findViewById(R.id.imageView);
        //image.setImageResource(0);

        updateGameState();
    }

    private void generatePuzzle(LinearLayout layout) {
        String currentWord = gameModel.getCurrentWord();

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
