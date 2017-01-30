package exam.review.leetcode.recursive.dfs;

/**
 * Created by shanwu on 17-1-30.
 */
public class TargetSum {
    int result = 0;
    public int findTargetSumWays(int[] nums, int target) {
        if(nums == null || nums.length ==0) {
            return 0;
        }

        findSum(nums,target,0,0);
        return result;
    }

    public void findSum(int[] nums, int target, int pos, int curVal) {
        if(pos == nums.length) {
            if(target == curVal) result++;
            return;
        }

        findSum(nums, target, pos+1, curVal+nums[pos]);
        findSum(nums, target, pos+1, curVal-nums[pos]);
    }
}
