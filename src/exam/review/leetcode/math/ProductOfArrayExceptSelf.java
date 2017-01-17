package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-1-17.
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        final int size = nums.length;
        int[] res = new int[size];

        res[0] = 1;
        for(int i =1; i < size; i++) {
            res[i] = res[i-1] * nums[i-1];
        }

        int right = 1;
        for(int i = size-1; i>=0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }

        return res;
    }
}
