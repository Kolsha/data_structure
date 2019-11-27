package exam.review.leetcode.arrays.find_duplicates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by shanwu on 17-1-15.
 * PC: 1
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

    /**
     * According to provided condition, we have an array of numbers, 1 <= value of number <= sizeOfArray
     * from the condition, we can use the value of the number as index in the array, negate the value it indicates,
     * therefore when we iterate through the array, once we find the negative value, we will know it's duplicated number
     * @param nums
     * @return
     */
    public List<Integer> findDuplicatesII(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        final int size = nums.length;
        for(int i = 0; i < size; i++) {
            final int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0) {
                // negate the value to mark
                nums[index] = - nums[index];
            } else {
                // if nums[index] is negative means we already have the value
                result.add(Math.abs(nums[i]));
            }
        }

        return result;
    }
}
