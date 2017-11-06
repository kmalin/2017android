package lt.birziska.minisweeper;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    // size of the grid of the crates
    // if size is 5, then the grid will be of size 5 * 5, 25 crates in total
    int size = 5;

    // how many bombs to plant when the game is generated
    int bombCount = 7;

    // how many crates needs to be opened for victory
    int createsToOpen = 10;

    // two-dimensional boolean array of size specified above
    // if value is true, then the cell has a bomb
    // if value is false, then the cell doesn't have a bomb
    boolean[][] bombArray;

    // one-dimensional array of length - size * size
    boolean[] wasChecked;

    // represents the state that the game has ended
    // after initGame method is called, gameEnded value becomes false
    // after setGameLost or setGameWon are called, gameEnded value becomes true
    boolean gameEnded = false;

    // keeps track of the number of opened crates
    int cratesOpened = 0;

    /*
    Method that initializes view on the start of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button restartGameButton = (Button) findViewById(R.id.restartGameButton);
        restartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });

        initGame();
    }

    /*
    Method that handles the click of the crate.
     */
    public void onCrateClick(View v){
        ImageButton button = (ImageButton)v;
        // id of the button has values from 0 to size * size - 1
        int id = button.getId();

        // get the row and the column of the cell

        // if game is ended, then nothing should be done in this event
        // if you already clicked on this crate before, then nothing should be done in this event

        // check if there is a bomb in this cell
        // if there is a bomb, then set the bomb image and end the game with a failure state

        // if there isn't a bomb in this cell, then:
        // calculate and show opened crates count
        // put appropriate image on the image button
        // if victory condition is reached, end the game with the winning state
    }

    /*
    Gets the image resource for an empty crate. There won't be a bomb in it.
    If there is no neighbouring bombs, then there should be no image.
    In this case value 0 removes the crate image.
    If there are 1 to 8 neighbouring bombs, then appropriate image should be returned.
     */
    private int getResourceIdByCount(int count) {

        switch (count){
            case 1:
                return R.drawable.count_one;
            case 2:
                return R.drawable.count_two;
            case 3:
                return R.drawable.count_three;
            case 4:
                return R.drawable.count_four;
            case 5:
                return R.drawable.count_five;
            case 6:
                return R.drawable.count_six;
            case 7:
                return R.drawable.count_seven;
            case 8:
                return R.drawable.count_eight;
            default:
                return 0;
        }
    }

    /*
    Calculates the number of bombs in all 8 neighbouring cells
     */
    private int bombCountInNeighbouringCells(int row, int column){
        int bombCount =
            bombCountInACell(row - 1, column - 1) +
            bombCountInACell(row - 1, column) +
            bombCountInACell(row - 1, column + 1) +
            bombCountInACell(row, column - 1) +
            bombCountInACell(row, column + 1) +
            bombCountInACell(row + 1, column - 1) +
            bombCountInACell(row + 1, column) +
            bombCountInACell(row + 1, column + 1);

        return bombCount;
    }

    /*
    Checks how many bombs does the cell have.
    If cell is out of bounds or has no bombs, then it returns 0.
    If cell has a bomb, then it returns 1.
     */
    private int bombCountInACell(int row, int column){
        if (row >= size || row < 0){
            return 0;
        }
        if (column >= size || column < 0){
            return 0;
        }
        return bombArray[row][column] ? 1 : 0;
    }

    /*
    Sets the game is lost state.
    Shows the game over text. And sets gameEnded flag.
     */
    private void setGameLost() {
        TextView gameOverTextView = (TextView) findViewById(R.id.gameOverTextView);
        gameOverTextView.setText("You lost.");

        View gameOverLayout = findViewById(R.id.gameOverLayout);
        gameOverLayout.setVisibility(View.VISIBLE);

        gameEnded = true;
    }

    /*
    Sets the game is won state.
    Shows the game over text. And sets gameEnded flag.
     */
    private void setGameWon() {
        TextView gameOverTextView = (TextView) findViewById(R.id.gameOverTextView);
        gameOverTextView.setText("You won.");

        View gameOverLayout = findViewById(R.id.gameOverLayout);
        gameOverLayout.setVisibility(View.VISIBLE);

        gameEnded = true;
    }

    /*
    Initializes the game.
     */
    private void initGame() {
        // hides game over text
        View gameOverLayout = findViewById(R.id.gameOverLayout);
        gameOverLayout.setVisibility(View.INVISIBLE);

        // resets cratesOpened count
        cratesOpened = 0;
        TextView cratesOpenedCount = (TextView) findViewById(R.id.cratesOpenedCount);
        cratesOpenedCount.setText(Integer.toString(cratesOpened));

        // clears all existing crates in the grid
        GridLayout layout = (GridLayout) findViewById(R.id.gameGridLayout);
        layout.removeAllViewsInLayout();

        // Fills in new creates
        fillInCrates(layout);
        // Generates bombs
        fillInBombArray();

        gameEnded = false;
    }

    /*
    Generates bombs array.
    Creates two-dimensional array for bombs.
    And one dimensional array for registering already checked crates.
     */
    private void fillInBombArray() {
        // initialize bomb array
        bombArray = new boolean[size][size];
        for(int i = 0; i < size; i++){
            bombArray[i] = new boolean[size]; // initially there will be no bombs
        }

        // creates another array with all of the indexes
        int sizeSquared = size * size;
        int[] bombSelector = new int[sizeSquared];
        for (int i = 0; i < sizeSquared; i++){
            bombSelector[i] = i;
        }
        for (int i = 0; i < bombCount; i++){
            // selects one item from the array of indexes
            // each iteration takes the indexes from the shorter and shorter array
            int selectedIndex = (int)(Math.random() * (sizeSquared - i));
            // gets the index
            int bombIndex = bombSelector[selectedIndex];

            int row = bombIndex / size;
            int column = bombIndex % size;
            // plants the bomb
            bombArray[row][column] = true;
            // move the index that was not picked from the end of the array to the place
            // where picked up index was located before
            // next iteration in the cycle will select from one elemement smaller array
            bombSelector[selectedIndex] = bombSelector[sizeSquared - i - 1];
        }

        // initializes array for registering already checked creates
        wasChecked = new boolean[size * size];
    }

    /*
    Generates crates UI elements
     */
    private void fillInCrates(GridLayout layout) {

        // measure the width of the screen
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int width = displaySize.x;
        int height = displaySize.y;
        // leave some space for global padding
        int layoutWidth = width - 32;
        // leave 2 + 2 pixes for the space between crates
        int singleCrateWidth = layoutWidth / size - 4;

        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                ImageButton crate = new ImageButton(this);
                crate.setImageResource(R.drawable.crate_200);
                crate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCrateClick(v);
                    }
                });
                crate.setBackgroundColor(Color.TRANSPARENT);
                crate.setScaleType(ImageView.ScaleType.FIT_CENTER);
                crate.setPadding(2,2,2,2);
                // put appropriate index on to the crate button
                int id = row * size + column;
                crate.setId(id);

                GridLayout.Spec rowSpec = GridLayout.spec(row);
                GridLayout.Spec columnSpec = GridLayout.spec(column);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.width = singleCrateWidth;
                params.height = singleCrateWidth;
                layout.addView(crate, params);
            }
        }
    }
}
