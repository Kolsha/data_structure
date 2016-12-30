package exam.review.leetcode.arrays;

import java.util.HashMap;

/**
 * Created by shanwu on 16-12-29.
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        int max =0;
        int max_value = 0;
        for(int i =0 ; i < nums.length; i++) {
            int count = (map.get(nums[i]) == null) ? 1 : map.get(nums[i]) + 1;
            if(count > max) {
                max = count;
                max_value = nums[i];
            }
            map.put(nums[i],count);
        }

        return max_value;
    }
}
