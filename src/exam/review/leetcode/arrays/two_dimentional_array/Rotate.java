package exam.review.leetcode.arrays.two_dimentional_array;

/**
 * Created by shanwu on 17-2-12.
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        final int row = matrix.length;
        final int col = matrix[0].length;

        // transpose
        for(int i = 0; i < row; i++) {
            for(int j = i; j < col; j++) {
                final int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // horizontally flip
        for(int i = 0; i< row; i++) {
            for(int j = 0; j < col/2; j++) {
                final int temp = matrix[i][j];
                matrix[i][j] = matrix[i][col-1-j];
                matrix[i][col-1-j] = temp;
            }
        }

    }
}
