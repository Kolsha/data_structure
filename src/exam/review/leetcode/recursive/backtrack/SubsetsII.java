package exam.review.leetcode.recursive.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shanwu on 17-1-20.
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtrack(result, new ArrayList(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList(temp));
        final int size = nums.length;
        for(int i = start; i < size; i++) {
            if(i > start && nums[i] == nums[i-1]) continue; // todo why?
            temp.add(nums[i]);
            backtrack(result, temp,nums,i+1);
            temp.remove(temp.size()-1);
        }
    }
}
