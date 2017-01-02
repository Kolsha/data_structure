package exam.review.leetcode.power_of_x;

/**
 * Created by shanwu on 17-1-2.
 */
public class PowerOfFour {
    public boolean isPowerOfFour(int n) {
        if(n==0) return false;

        while(n%4==0) {
            n = n/4;
        }

        return n == 1;
    }
}
