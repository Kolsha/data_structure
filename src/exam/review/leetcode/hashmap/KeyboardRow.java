package exam.review.leetcode.hashmap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanwu on 17-3-1.
 */
public class KeyboardRow {
    private final static String mFirstRow = "QWERTYUIOP";
    private final static String mSecondRow = "ASDFGHJKL";
    private final static String mThirdRow = "ZXCVBNM";

    public static String[] findWords(String[] words) {
        HashMap<Character, Integer> mMap = new HashMap<>();
        initMap(mMap);
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            String upTemp = temp.toUpperCase();
            if (isMatch(upTemp, mMap)) {
                result.add(temp);
            }
        }

        String[] resultArray = new String[result.size()];
        for(int i = 0; i < resultArray.length;i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    public static void initMap(HashMap<Character, Integer> mMap) {
        for (int i = 0; i < mFirstRow.length(); i++) {
            mMap.put(mFirstRow.charAt(i), 1);
        }

        for (int i = 0; i < mSecondRow.length(); i++) {
            mMap.put(mSecondRow.charAt(i), 2);
        }

        for (int i = 0; i < mThirdRow.length(); i++) {
            mMap.put(mThirdRow.charAt(i), 3);
        }
    }

    public static boolean isMatch(String temp, HashMap<Character, Integer> mMap) {
        int row = 0;
        for (int i = 0; i < temp.length(); i++) {
            char tempChar = temp.charAt(i);
            int tempRow = mMap.get(tempChar);
            if (row == 0) {
                row = tempRow;
            }
            if (row != tempRow) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"}));
    }
}
