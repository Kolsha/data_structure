package exam.review.leetcode.arrays;

import java.util.Arrays;

/**
 * Created by shanwu on 16-12-17.
 */
public class RemoveElements {

    /** Given an array and a value, remove all instances of that value in place and return
     *  the new length. Do not allocate extra space for another array, you must do this in
     *  place with constant memory.The order of elements can be changed. It doesn't matter
     *  what you leave beyond the new length.Example:Given input array nums = [3,2,2,3], val = 3
     *  Your function should return length = 2, with the first two elements of nums being 2.
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        final int size = nums.length;
        int updatedSize = 0;
        for (int i = 0; i < size; i++) {
            if (nums[i] != val) {
                nums[updatedSize++] = nums[i];
            }
        }
        return updatedSize;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        System.out.println(removeElement(nums, 3) + " nums: " + Arrays.toString(nums));
    }
}
