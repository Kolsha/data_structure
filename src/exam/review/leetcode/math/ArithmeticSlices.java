package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-1-17.
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, result = 0;

        final int size = A.length;
        for(int i = 2; i < size; i++) {
            if(A[i] - A[i-1] == A[i-1] - A[i-2]) {
                curr += 1;
                result += curr;
            } else {
                curr = 0;
            }
        }

        return result;

    }
}
