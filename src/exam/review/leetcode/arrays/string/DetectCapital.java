package exam.review.leetcode.arrays.string;

/**
 * Created by shanwu on 17-3-1.
 */
public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) ||
                word.equals(word.toLowerCase()) ||
                Character.isUpperCase(word.charAt(0)) &&
                        word.substring(1).equals(word.substring(1).toLowerCase());
    }
}
