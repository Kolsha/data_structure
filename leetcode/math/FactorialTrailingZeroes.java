package exam.review.leetcode.math;

/** todo: try to figure out this math question...
 * Created by shanwu on 17-1-11.
 */
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int r = 0;
        while(n > 0) {
            n = n/5;
            r+=n;
        }
        return r;
    }
}
