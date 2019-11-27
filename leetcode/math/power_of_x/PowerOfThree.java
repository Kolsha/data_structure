package exam.review.leetcode.math.power_of_x;

/**
 * Created by shanwu on 16-12-30.
 * PC: 1
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;

    }
}
