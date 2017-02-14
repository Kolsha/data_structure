package exam.review.leetcode.math.power_of_x;

/**
 * Created by shanwu on 16-12-30.
 * PC: 1
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if(n ==0) {
            return false;
        }

        while(n%2==0) {
            n /= 2;
        }

        return n == 1;

    }
}
