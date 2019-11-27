package exam.review.leetcode.easy;

/**
 * Created by shanwu on 17-1-9.
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int x = n;
        int y = n;
        while(x > 1) {
            x = cal(x);
            if(n == 1) {
                return true;
            }

            y = cal(cal(y));
            if (y == 1) {
                return true;
            }

            if(x== y) {
                return false;
            }
        }

        return true;
    }

    public int cal(int x) {
        int s = 0;
        while(x!=0) {
            s = s + (x%10)*(x%10);
            x = x/10;
        }
        return s;
    }
}
