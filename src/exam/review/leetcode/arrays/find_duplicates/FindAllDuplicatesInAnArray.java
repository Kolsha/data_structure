package exam.review.leetcode.arrays.find_duplicates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by shanwu on 17-1-15.
 */
public class FindAllDuplicatesInAnArray {
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> result = new ArrayList();
        if(nums == null || nums.length == 0) {
            return result;
        }

        HashSet<Integer> set = new HashSet();
        HashSet<Integer> temp = new HashSet();

        for(int n:nums) {
            if(!set.add(n)) {
                temp.add(n);
            }
        }

        for(Integer s:temp) {
            result.add(s);
        }


        return result;

    }
}
