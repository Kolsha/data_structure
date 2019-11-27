package exam.review.leetcode.recursive;

/**
 * Created by shanwu on 17-1-9.
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n == 0 || n == 1 || n == 2) {
            return n;
        }

        int[] method = new int[n];
        method[0] = 1;
        method[1] = 2;
        for(int i = 2; i < n; i++) {
            method[i] = method[i-1] + method[i-2];
        }
        return method[n-1];
    }
}
