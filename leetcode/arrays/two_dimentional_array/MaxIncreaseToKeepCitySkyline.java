package exam.review.leetcode.arrays.two_dimentional_array;

/**
 * https://leetcode.com/problems/max-increase-to-keep-city-skyline/discuss/120681/Easy-and-Concise-Solution-C++JavaPython
 Idea:
 For grid[i][j], it can't be higher than the maximun of its row nor the maximum of its col.
 So the maximum increasing height for a building at (i, j) is min(row[i], col[j]) - grid[i][j]

 Codes:
 row: maximum for every row
 col: maximum for every col
 The fisrt loop of grid calcule maximum for every row and every col.
 The second loop calculate the maximum increasing height for every building.
 */
public class MaxIncreaseToKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] col = new int[n], row = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res += Math.min(row[i], col[j]) - grid[i][j];
        return res;
    }
}