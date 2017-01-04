package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-4.
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for(int i =0; i < nums.length; i++) {
            if(nums[i] == 0) {
                continue;
            } else {
                nums[j++] = nums[i];
            }
        }

        while(j < nums.length) {
            nums[j] = 0;
            j++;
        }

    }
}
