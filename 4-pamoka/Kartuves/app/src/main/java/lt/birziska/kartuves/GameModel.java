package lt.birziska.kartuves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {

    private WordList wordList;

    // Number of tries allowed
    private final int MAX_TRY_COUNT = 9;

    // the length of the current word
    private int wordLength;
    // the current word
    private String currentWord;

    // shows whether the game ended or not
    private boolean gameEnded;
    // shows whether the game was won or not
    private boolean gameWon;
    // lists the current state of the letters in a word
    // if letter was guessed then it is shown
    // if letter was not guessed then '_' symbol is shown instead
    private Character[] currentlyOpenedWord;
    // keeps track of the correctly opened letter count
    private int correctlyOpenedLetterCount;
    // keeps track of the incorrectly guessed letter count
    private int incorrectlyGuessedLetterCount;
    // lists all unique guessed letters to avoid guessing the same letter twice
    private List<Character> allGuessedLetters;
    // lists all the incorrectly guessed letters to show to user
    private List<Character> incorrectlyGuessedLetters;

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