package lt.birziska.kartuves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {

    private String currentWord;
    private List<Character> allGuessedLetters;
    private List<Character> incorrectlyGuessedLetters;

    private WordList wordList;
    private int wordLength;

    private final int MAX_TRY_COUNT = 9;

    private boolean gameEnded;
    private boolean gameWon;
    private Character[] currentlyOpenedWord;
    private int correctlyOpenedLetterCount;
    private int incorrectlyGuessedLetterCount;

    public GameModel(WordList wordList){
        this.wordList = wordList;
    }

    public void initializeGame(int wordLength){
        this.wordLength = wordLength;
        gameEnded = false;
        gameWon = false;

        allGuessedLetters = new ArrayList<Character>();
        incorrectlyGuessedLetters = new ArrayList<Character>();
        incorrectlyGuessedLetterCount = 0;
        correctlyOpenedLetterCount = 0;

        currentWord = wordList.pickRandomWord(this.wordLength).toLowerCase();
        currentlyOpenedWord = new Character[currentWord.length()];
        Arrays.fill(currentlyOpenedWord, '_');
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void submitLetter(Character guessedLetter) {

        if (allGuessedLetters.contains(guessedLetter)){
            return;
        }
        allGuessedLetters.add(guessedLetter);

        if (GameHelpers.wordContainsLetter(guessedLetter, currentWord)){

            for(int i = 0; i < currentWord.length(); i++){
                char letterInAWord = currentWord.charAt(i);

                if (letterInAWord == guessedLetter){
                    correctlyOpenedLetterCount++;
                    currentlyOpenedWord[i] = guessedLetter;
                }
            }

            if (correctlyOpenedLetterCount == currentWord.length()){
                gameEnded = true;
                gameWon = true;
            }
        }
        else {
            incorrectlyGuessedLetterCount++;
            incorrectlyGuessedLetters.add(guessedLetter);
            if (incorrectlyGuessedLetterCount >= MAX_TRY_COUNT){
                gameEnded = true;
                gameWon = false;

                for(int i = 0; i < currentWord.length(); i++){
                    char letterInAWord = currentWord.charAt(i);
                    currentlyOpenedWord[i] = letterInAWord;
                }
            }
        }
    }

    public boolean getGameEnded() {
        return gameEnded;
    }

    public boolean getGameWon() {
        return gameWon;
    }

    public Character[] getCurrentlyOpenedWord() {
        return currentlyOpenedWord;
    }

    public Character[] getIncorrectlyGuessedLetters() {
        Character[] result = new Character[incorrectlyGuessedLetters.size()];
        result = incorrectlyGuessedLetters.toArray(result);
        return result;
    }

    public int getIncorrectlyGuessedLetterCount() {
        return incorrectlyGuessedLetterCount;
    }
}