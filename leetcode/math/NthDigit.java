package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-3-6.
 */
public class NthDigit {
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while(n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }

        start = start + (n - 1)/len; // why?

        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n-1)%len));
    }
}
