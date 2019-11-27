package exam.review.leetcode.hashmap;

import java.util.HashMap;

/**
 * Created by shanwu on 17-1-2.
 */
public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            Object val = map.get(nums[i]);
            if(val == null) {
                map.put(nums[i], i);
            } else {
                int preIndex = (int) val;
                int diff = i - preIndex;
                if(diff <= k) {
                    return true;
                } else {
                    map.put(nums[i],i);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[] {1,0,1,1,},1));
    }
}
