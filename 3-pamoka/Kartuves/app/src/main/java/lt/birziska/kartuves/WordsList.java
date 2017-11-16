package lt.birziska.kartuves;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class WordsList {
    public static String GetGamesWord()
    {
        String[] words = new String []{
                "cat",
                "sun",
                "cup",
                "ghost",
                "pie",
                "flower",
                "cow",
                "banana",
                "snowflake",
                "jar",
                "bug",
                "book",
                "snake",
                "light",
                "tree",
                "lips",
                "apple",
                "slide",
                "socks",
                "smile",
                "swing",
                "coat",
                "shoe",
                "water",
                "heart",
                "hat",
                "ocean",
                "kite",
                "mouth",
                "dog",
                "milk",
                "duck",
                "eyes",
                "skateboard",
                "bird",
                "boy",
                "person",
                "girl",
                "mouse",
                "house",
                "ball",
                "nose",
                "star",
                "bed",
                "whale",
                "jacket",
                "shirt",
                "hippo",
                "beach",
                "egg",
                "face",
                "cookie",
                "cheese",
                "drum",
                "circle",
                "spoon",
                "worm"
        };
        String word = words[(int) (Math.random() * words.length)];
        return word;
    }
}
