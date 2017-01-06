package exam.review.leetcode.arrays;

import java.util.Arrays;

/** read this https://en.wikipedia.org/wiki/Permutation if you don't have any clue
 * Created by shanwu on 17-1-6.
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int k = -1;
        final int size = nums.length;
        for (int i = 1; i < size; i++) {
            if (nums[i - 1] < nums[i]) {
                k = i - 1;
            }
        }

        if (k == -1) {
            Arrays.sort(nums);
            return;
        }

        int l = -1;
        for (int j = size - 1; j > k; j--) {
            if (nums[k] < nums[j]) {
                l = j;
                break;
            }
        }

        // swap k, l
        swap(nums, k, l);

        // reverse k+1~ end
        reverse(nums, k + 1, nums.length-1);

    }

    public static void swap(int[] nums, int k, int l) {
        int temp = nums[k];
        nums[k] = nums[l];
        nums[l] = temp;
    }

    public static void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        nextPermutation(nums);
        //reverse(nums,0);
        System.out.println(Arrays.toString(nums));
    }
}
