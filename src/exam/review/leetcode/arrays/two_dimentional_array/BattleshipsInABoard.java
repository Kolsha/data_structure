package exam.review.leetcode.arrays.two_dimentional_array;

/**
 * Created by shanwu on 17-1-12.
 */
public class BattleshipsInABoard {
    public int countBattleships(char[][] board) {
        if(board == null || board.length < 1) {
            return 0;
        }

        final int row = board.length;
        final int col = board[0].length;

        int result = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if( board[i][j] == '.') continue;
                if( i > 0 && board[i-1][j] == 'X') continue;
                if( j > 0 && board[i][j-1] == 'X') continue;

                result++;
            }
        }
        return result;
    }
}
