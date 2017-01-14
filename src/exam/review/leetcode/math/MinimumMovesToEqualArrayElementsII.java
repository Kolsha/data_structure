package exam.review.leetcode.math;

import java.util.Arrays;

/**
 * Created by shanwu on 17-1-14.
 */
public class MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        final int size = nums.length;
        int i = 0;
        int j = size -1;
        int count =0;
        while(i < j) {
            count += (nums[j--] - nums[i++]);
        }
        return count;
    }
}
