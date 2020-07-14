### 286. Walls and Gates
https://leetcode.com/problems/walls-and-gates/

You are given a m x n 2D grid initialized with these three possible values.

1. `-1` - A wall or an obstacle.
2. `0` - A gate.
3. `INF` - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:
```
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
```
After running your function, the 2D grid should be:
```
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
```

Solution

Approach 1: Breadth-first Search

Instead of searching from an empty room to the gates, how about searching the other way round? In other words, we initiate breadth-first search (BFS) from all gates at the same time. Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, the distance to an empty room must be the shortest.


Complexity Analysis

<ul>
<li>
<p>Time complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>m</mi><mi>n</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(mn)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault">m</span><span class="mord mathdefault">n</span><span class="mclose">)</span></span></span></span>.</p>
<p>If you are having difficulty to derive the time complexity, start simple.</p>
<p>Let us start with the case with only one gate. The breadth-first search takes at most <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>m</mi><mo>×</mo><mi>n</mi></mrow><annotation encoding="application/x-tex">m \times n</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.66666em;vertical-align:-0.08333em;"></span><span class="mord mathdefault">m</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span><span class="mbin">×</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span></span><span class="base"><span class="strut" style="height:0.43056em;vertical-align:0em;"></span><span class="mord mathdefault">n</span></span></span></span> steps to reach all rooms, therefore the time complexity is <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>m</mi><mi>n</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(mn)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault">m</span><span class="mord mathdefault">n</span><span class="mclose">)</span></span></span></span>. But what if you are doing breadth-first search from <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>k</mi></mrow><annotation encoding="application/x-tex">k</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.69444em;vertical-align:0em;"></span><span class="mord mathdefault" style="margin-right:0.03148em;">k</span></span></span></span> gates?</p>
<p>Once we set a room's distance, we are basically marking it as visited, which means each room is visited at most once. Therefore, the time complexity does not depend on the number of gates and is <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>m</mi><mi>n</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(mn)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault">m</span><span class="mord mathdefault">n</span><span class="mclose">)</span></span></span></span>.</p>
</li>
<li>
<p>Space complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>m</mi><mi>n</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(mn)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault">m</span><span class="mord mathdefault">n</span><span class="mclose">)</span></span></span></span>.
The space complexity depends on the queue's size. We insert at most <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>m</mi><mo>×</mo><mi>n</mi></mrow><annotation encoding="application/x-tex">m \times n</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.66666em;vertical-align:-0.08333em;"></span><span class="mord mathdefault">m</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span><span class="mbin">×</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span></span><span class="base"><span class="strut" style="height:0.43056em;vertical-align:0em;"></span><span class="mord mathdefault">n</span></span></span></span> points into the queue.</p>
</li>
</ul>
```java
class Solution {
    private final static int EMPTY_ROOM = Integer.MAX_VALUE;
    private final static int GATE = 0;
    private final static int WALL = -1;

    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0) {
            return;
        }

        int rowMax = rooms.length;
        int colMax = rooms[0].length;
        
        // Find the gate and put them in the queue for later BFS use
        // int[] -> {row, col}
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < rowMax; i++) {
            for(int j = 0; j < colMax; j++) {
                if(rooms[i][j] == GATE) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        
        int[][] dirs = new int[][] {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
        };

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();
            int row = loc[0];
            int col = loc[1];
            
            // go for 4 different directions to find "EMPTY ROOM"
            for(int i = 0; i < dirs.length; i++) {
                int nextRow = row + dirs[i][0];
                int nextCol = col + dirs[i][1];
                if(nextRow < 0 || nextRow >= rowMax || nextCol < 0 || nextCol >= colMax 
                   || rooms[nextRow][nextCol] != EMPTY_ROOM) {
                    continue;
                }
                
                // if we found the empty room, then add 1 for the distance
                rooms[nextRow][nextCol] = rooms[row][col] + 1;
                queue.offer(new int[] {nextRow, nextCol});
            }
        }
    }
}
```