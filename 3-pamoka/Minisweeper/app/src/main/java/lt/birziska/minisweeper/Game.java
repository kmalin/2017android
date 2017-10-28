package lt.birziska.minisweeper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    int size = 5;
    int bombCount = 7;
    boolean[][] bombArray;

    boolean gameEnded = false;
    int cratesOpened = 0;

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

    class CrateClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (gameEnded) {
                return;
            }

            ImageButton button = (ImageButton)v;

            int id = button.getId();
            int row = id / size;
            int column = id % size;

            boolean hasBomb = bombArray[row][column];

            if (hasBomb) {
                button.setImageResource(R.drawable.bomb_200);
                gameLost();
            } else {
                cratesOpened++;
                TextView cratesOpenedCount = (TextView) findViewById(R.id.cratesOpenedCount);
                cratesOpenedCount.setText(Integer.toString(cratesOpened));

                int count = calculateCountOfNeighbouringBombs(row, column);

                int restId = getResourceIdByCount(count);
                button.setImageResource(restId);

                if (cratesOpened > 10) {
                    gameWon();
                }
            }
        }
    }

    private int getResourceIdByCount(int count) {

        switch (count){
            case 1:
                return R.drawable.count_1;
            case 2:
                return R.drawable.count_2;
            case 3:
                return R.drawable.count_3;
            case 4:
                return R.drawable.count_4;
            case 5:
                return R.drawable.count_5;
            case 6:
                return R.drawable.count_6;
            case 7:
                return R.drawable.count_7;
            case 8:
                return R.drawable.count_8;
            default:
                return 0;
        }
    }

    private int calculateCountOfNeighbouringBombs(int row, int column){
        int bombCount =
            hasBomb(row - 1, column - 1) +
            hasBomb(row - 1, column) +
            hasBomb(row - 1, column + 1) +
            hasBomb(row, column - 1) +
            hasBomb(row, column + 1) +
            hasBomb(row + 1, column - 1) +
            hasBomb(row + 1, column) +
            hasBomb(row + 1, column + 1);

        return bombCount;
    }

    private int hasBomb(int row, int column){
        if (row >= size || row < 0){
            return 0;
        }
        if (column >= size || column < 0){
            return 0;
        }
        return bombArray[row][column] ? 1 : 0;
    }

    private void initGame() {
        View gameOverLayout = findViewById(R.id.gameOverLayout);
        gameOverLayout.setVisibility(View.INVISIBLE);

        cratesOpened = 0;
        TextView cratesOpenedCount = (TextView) findViewById(R.id.cratesOpenedCount);
        cratesOpenedCount.setText(Integer.toString(cratesOpened));

        GridLayout layout = (GridLayout) findViewById(R.id.gameGridLayout);
        layout.removeAllViewsInLayout();

        fillInCrates(layout);
        fillInBombArray();

        gameEnded = false;
    }

    private void gameLost() {
        TextView gameOverTextView = (TextView) findViewById(R.id.gameOverTextView);
        gameOverTextView.setText("You lost.");

        View gameOverLayout = findViewById(R.id.gameOverLayout);
        gameOverLayout.setVisibility(View.VISIBLE);

        gameEnded = true;
    }

    private void gameWon() {
        TextView gameOverTextView = (TextView) findViewById(R.id.gameOverTextView);
        gameOverTextView.setText("You won.");

        View gameOverLayout = findViewById(R.id.gameOverLayout);
        gameOverLayout.setVisibility(View.VISIBLE);

        gameEnded = true;
    }

    private void fillInBombArray() {
        // initialize bomb array
        bombArray = new boolean[size][size];
        for(int i = 0; i < size; i++){
            bombArray[i] = new boolean[size]; // initially there will be no bombs
        }

        int sizeSquared = size * size;
        int[] bombSelector = new int[sizeSquared];
        for (int i = 0; i < sizeSquared; i++){
            bombSelector[i] = i;
        }
        for (int i = 0; i < bombCount; i++){
            int selectedIndex = (int)(Math.random() * (sizeSquared - i));
            int bombIndex = bombSelector[selectedIndex];

            int row = bombIndex / size;
            int column = bombIndex % size;
            bombArray[row][column] = true; // plant a bomb

            bombSelector[selectedIndex] = bombSelector[sizeSquared - i - 1];
        }
    }

    private void fillInCrates(GridLayout layout) {

        for(int row = 0; row < size; row++){
            for(int column = 0; column < size; column++){
                ImageButton crate = new ImageButton(this);
                crate.setImageResource(R.drawable.crate_200);
                crate.setOnClickListener(new CrateClick());
                crate.setBackgroundColor(Color.TRANSPARENT);
                crate.setScaleType(ImageView.ScaleType.FIT_CENTER);
                crate.setPadding(2,2,2,2);
                int id = row * size + column;
                crate.setId(id);

                GridLayout.Spec rowSpec = GridLayout.spec(row);
                GridLayout.Spec columnSpec = GridLayout.spec(column);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.width = 200;
                params.height = 200;
                layout.addView(crate, params);
            }
        }
    }
}
