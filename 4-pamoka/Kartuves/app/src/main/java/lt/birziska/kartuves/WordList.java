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

        int[] lengths = new int[16];
        for (String word: words) {
            if (word.length() == length){
                correctLengthWords.add(word);
            }
            lengths[word.length()]++;
        }

        String word = correctLengthWords.get(getRandomIndex(correctLengthWords.size()));

        return word;
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
