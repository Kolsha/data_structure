package exam.review.leetcode.easy;

/**
 * Created by shanwu on 17-1-9.
 */
public class ArrangingCoins {
    public int arrangeCoins(int n) {
        int i = 0;
        int sum = 0;
        int remain = n;
        while(remain > 0) {
            sum = sum + (++i);
            remain = remain - i;
            if(remain < 0) {
                return i -1;
            } else if (remain == 0) {
                return i;
            }
        }

        return 0;
    }
}
