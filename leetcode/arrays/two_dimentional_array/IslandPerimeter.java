package exam.review.leetcode.arrays.two_dimentional_array;

/**
 * Created by shanwu on 17-1-12.
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length  < 1) {
            return 0;
        }

        final int row = grid.length;
        final int col = grid[0].length;

        int count =0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 0) continue;

                // top
                int top = ( i > 0 && grid[i-1][j] == 1 ) ? 0:1;

                // bottom
                int bottom = (i+1 < row && grid[i+1][j] == 1) ? 0:1;

                // left
                int left = (j >0 && grid[i][j-1] == 1 ) ? 0:1;

                // right
                int right = (j+1 < col && grid[i][j+1] == 1) ? 0:1;

                count = count + top + bottom + left + right;
            }
        }

        return count;
    }
}
