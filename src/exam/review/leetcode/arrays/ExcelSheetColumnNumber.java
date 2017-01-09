package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-9.
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int result = 0;
        final int size = s.length();
        for(int i = 0; i < size; i++) {
            char temp = s.charAt(i);
            result = result * 26 + (temp - 'A' + 1);
        }

        return result;
    }
}
