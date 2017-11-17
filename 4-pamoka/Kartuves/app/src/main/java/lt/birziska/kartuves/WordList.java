package lt.birziska.kartuves;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordList {

    private String[] words;

    public WordList(AssetManager assets){
        initializeWordList(assets);
    }

    public String pickRandomWord(int length)
    {
        List<String> correctLengthWords = new ArrayList<String>();

        // use for loop to select words having correct size

        // put those words into a list correctLengthWords

        // select random word from the list
        // useful functions:
        // correctLengthWords.size() - size of the list
        // correctLengthWords.get(index) - gets the element at the position index
        // getRandomIndex(size) - picks an integer number from 0 to size - 1

        return "replace"; // replace this with your implementation
    }

    private int getRandomIndex(int size){
        return (int) (Math.random() * size);
    }

    private void initializeWordList(AssetManager assets) {
        List<String> wordList = new ArrayList<String>();
        String line = null;
        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(assets.open("word_list.txt")));

            while ((line = reader.readLine()) != null) {
                wordList.add(line);
            }
        }
        catch (IOException exc){
        }

        words = new String[wordList.size()];
        words = wordList.toArray(words);
    }
}
