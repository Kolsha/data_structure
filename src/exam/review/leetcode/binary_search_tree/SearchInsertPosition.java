package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-3-2.
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if(nums == null) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;

            if(target > nums[mid]) {
                left = mid+1;
            } else if (target < nums[mid]) {
                right = mid-1;
            } else {
                return mid;
            }
        }

        return left;
    }
}
