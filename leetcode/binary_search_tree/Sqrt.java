package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-1-12.
 * PC: 1
 */
public class Sqrt {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = x;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) { // it equals to mid * mid > x, but we make it "mid > x/mid " to prevent overflow
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) // it equals to (mid + 1)*(mid + 1) > x, but we make it "mid + 1 > x/mid+1" to prevent overflow
                    return mid;
                left = mid + 1;
            }
        }
    }
}
