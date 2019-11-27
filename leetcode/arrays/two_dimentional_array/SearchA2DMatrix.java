package exam.review.leetcode.arrays.two_dimentional_array;

/**
 * Created by shanwu on 17-1-24.
 */
public class SearchA2DMatrix {
    public boolean searchMatrixII(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        final int COL = matrix[0].length;
        final int ROW = matrix.length;
        int indexCol = COL - 1;
        int indexRow = 0;
        while(indexRow < ROW && indexCol >= 0) {
            if(matrix[indexRow][indexCol] == target) {
                return true;
            } else if (matrix[indexRow][indexCol] > target) {
                indexCol--;
            } else {
                indexRow++;
            }
        }
        return false;
    }
}
