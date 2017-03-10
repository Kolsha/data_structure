package exam.review.leetcode.arrays.permutation;

import java.util.Arrays;

/** read the following links
 * {@see <a href = "https://en.wikipedia.org/wiki/Permutation">wiki</a>}
 * {@see <a href="https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering">quroa</a>}
 * if you don't have any clue
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        // check boundary case
        if (nums == null || nums.length < 2) {
            return;
        }

        // 1. Find the largest x from the beginning of the array, such that P[k]<P[k+1].
        int k = -1;
        final int size = nums.length;
        for (int i = 1; i < size; i++) {
            if (nums[i - 1] < nums[i]) {
                k = i - 1;
            }
        }

        // If there is no such x, P is the last permutation.
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }

        // Find the first int P[l] from the end of the array, such that P[k]<P[l].
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
