package exam.review.leetcode.easy;

/**
 * Created by shanwu on 17-1-16.
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        final int size = nums.length;
        int count = 0;
        int result = 0;
        for(int i = 0; i < size; i++) {
            if(nums[i] == 1) {
                count++;
                if(result < count) {
                    result = count;
                }
            } else {
                count = 0;
            }

        }

        return result;
    }
}
