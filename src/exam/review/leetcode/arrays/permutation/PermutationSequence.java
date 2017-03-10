package exam.review.leetcode.arrays.permutation;

import java.util.Arrays;


/**
 * Created by shanwu on 17-1-6.
 * check {@link NextPermutation}
 * PC: 1
 */
public class PermutationSequence {
    public static String getPermutation(int n, int k) {
        // check edge case
        if(n == 0) {
            return null;
        } else if (n == 1) {
            return "1";
        }

        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }

        if(k == 1) {
            return arrayToString(nums);
        }

        while(--k >0) {
            nextPermutation(nums);
        }

        return arrayToString(nums);

    }

    public static String arrayToString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for(int d:nums) {
            sb.append(d);
        }
        return sb.toString();
    }

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int k = -1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i-1] < nums[i]) {
                k = i -1;
            }
        }

        if(k == -1) {
            Arrays.sort(nums);
            return;
        }

        int l = -1;
        for(int i = nums.length -1; i > k; i--) {
            if(nums[i] > nums[k]) {
                l = i;
                break;
            }
        }

        swap(nums, k, l);

        reverse(nums, k+1, nums.length-1);
    }

    private static void reverse(int[] nums,int start, int end) {
        while(end > start) {
            swap(nums, start++, end--);
        }
    }

    private static void swap(int[] nums, int k, int l) {
        int temp = nums[k];
        nums[k] = nums[l];
        nums[l] = temp;
    }

}