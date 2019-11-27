package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-7.
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        final int n = nums.length;
        int sum = (0+n)*(n+1)/2;

        int actualSum = 0;
        for(int s: nums) {
            actualSum+=s;
        }

        return sum-actualSum;

    }
}
