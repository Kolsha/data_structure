package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-1-12.
 */

/**
 * Adding 1 to n - 1 elements is the same as subtracting 1 from one element, w.r.t goal of making the elements in the array equal.
 */
public class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int n :nums) {
            if(min > n) {
                min = n;
            }
        }

        int res = 0;
        for(int n : nums) {
            res += (n - min);
        }

        return res;
    }
}
