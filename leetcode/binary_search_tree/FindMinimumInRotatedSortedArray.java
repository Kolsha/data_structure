package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-3-12.
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            int mid = (left + right)/2;

            if(mid > 0 && nums[mid-1] > nums[mid]) {
                return nums[mid];
            } else if(nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[left];
    }
}
