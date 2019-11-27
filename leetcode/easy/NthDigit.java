package exam.review.leetcode.easy;

/**
 * Created by shanwu on 16-12-31.
 */
public class NthDigit {

    // // TODO: 16-12-31 find out the reason why it exceed the time limit
    public static int findNthDigit(int n) {
        String val = "1";
        int i = 2;
        while (i < n+1) {
            val = val.concat(String.valueOf(i));
            i++;
        }

        return Character.getNumericValue(val.charAt(n-1));
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(3));
    }
}
