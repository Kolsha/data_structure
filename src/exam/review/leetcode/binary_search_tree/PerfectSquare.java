package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-1-23.
 * PC: 1
 */
public class PerfectSquare {
    public boolean ValidPerfectSquare(int num) {
        long left = 1, right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long t = mid * mid;
            if (t > num) {
                right = mid - 1;
            } else if (t < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
