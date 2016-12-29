package leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 16-12-29.
 */
public class FindAllNumDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }

        int resultSize = nums.length;
        int[] result = new int[nums.length+1];
        for(int i = 0; i < result.length; i++) {
            result[i] = 1;
        }

        for(int i = 0; i< nums.length; i++) {
            result[nums[i]] = 0;
            resultSize--;
        }

        ArrayList<Integer> list = new ArrayList();
        for(int i = 0; i < result.length; i++) {
            if(i >0 && result[i] == 1) {
                list.add(i);
            }
        }

        return list;
    }
}
