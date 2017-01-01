package exam.review.leetcode.arrays;

/**
 * Created by shanwu on 17-1-1.
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = m+n-1; i >= 0; i--) {
            if(m > 0 && n > 0 && nums1[m-1] >= nums2[n-1]) {
                nums1[i] = nums1[m-1];
                m--;
            } else if(m > 0 && n > 0 && nums1[m-1] < nums2[n-1]){
                nums1[i] = nums2[n-1];
                n--;
            } else {
                break;
            }
        }

        while( m > 0) {
            nums1[m+n-1] = nums1[--m];
        }

        while( n > 0) {
            nums1[m+n-1] = nums2[--n];
        }
    }
}
