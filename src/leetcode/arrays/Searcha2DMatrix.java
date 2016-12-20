package leetcode.arrays;

/**
 * Created by shanwu on 16-12-20.
 */
public class Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        final int row = matrix.length;
        if(row <= 0) {
            return false;
        }

        final int col = matrix[0].length;
        int cIndex = col -1;
        int rIndex = 0;

        while(rIndex < row) {
            if(matrix[rIndex][cIndex] == target) {
                return true;
            } else if(cIndex > 0) {
                cIndex--;
            } else if(rIndex < row) {
                rIndex++;
                cIndex = col -1;
            }
        }
        return false;
    }
}
