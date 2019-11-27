package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-1-24.
 * PC: 1
 */
public class findPeakElement {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;

        while(low < high) {
            int mid1 = low + (high - low)/2;
            int mid2 = mid1+1;

            if(nums[mid1] < nums[mid2]) {
                low = mid2;
            } else {
                high = mid1;
            }
        }
        return low;
    }
}
