### 1222. Queens That Can Attack the King
https://leetcode.com/problems/queens-that-can-attack-the-king/

On an 8x8 chessboard, there can be multiple Black Queens and one White King.

Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.

**Solution:**
```java
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        // init the chess board
        boolean[][] seen = new boolean[8][8];
        // place each queen on the chess board according to its x, y coordinates
        for (int[] queen : queens) {
            seen[queen[0]][queen[1]] = true;
        }

        // choices of directions: 
        // moving left -> -1, not moving -> 0, moving right -> 1
        // moving up -> -1, not moving -> 0, moving down -> 1 
        int[] dirs = { -1, 0, 1 };
        for (int dx : dirs) {
            for (int dy : dirs) {
                // skip not moving at all one, or it's gonna cause endless loop
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int x = king[0], y = king[1];
                while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8) {
                    x += dx;
                    y += dy;
                    // if new coordinates has a queen, add the coordinates to the list
                    // , then continue search other direction
                    if (seen[x][y]) {
                        result.add(Arrays.asList(x, y));
                        break;
                    }
                }
            }
        }
        return result;
    }
```