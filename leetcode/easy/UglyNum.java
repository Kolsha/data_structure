package exam.review.leetcode.easy;

/**
 * Created by shanwu on 17-1-1.
 */
public class UglyNum {
    public boolean isUgly(int n) {
        boolean dividable = true;
        while(n > 1 && dividable) {
            dividable = false;
            if(n % 2 == 0) {
                n /=2;
                dividable = true;
            }

            if(n % 3 == 0) {
                n /= 3;
                dividable = true;
            }

            if(n % 5 == 0) {
                n /= 5;
                dividable = true;
            }
        }

        return n == 1;
    }
}
