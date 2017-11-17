package lt.birziska.kartuves;

import java.util.List;

public class GameHelpers {
    public static boolean wordContainsLetter(char letter, String word){
        return word.indexOf(letter) > -1;
    }

    public static char toChar(String str){
        return str.toLowerCase().charAt(0);
    }

    public static int getResourceIdByCount(int count) {

        switch (count){
            case 1:
                return R.drawable.hang1;
            case 2:
                return R.drawable.hang2;
            case 3:
                return R.drawable.hang3;
            case 4:
                return R.drawable.hang4;
            case 5:
                return R.drawable.hang5;
            case 6:
                return R.drawable.hang6;
            case 7:
                return R.drawable.hang7;
            case 8:
                return R.drawable.hang8;
            case 9:
                return R.drawable.hang9;
            default:
                return 0;
        }
    }
}