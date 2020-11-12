### 353. Design Snake Game

https://leetcode.com/problems/design-snake-game/

Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
```
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
```

Solution

In java
```java
class SnakeGame {
    private int rows;
    private int cols;
    private int len;
    private int[][] foods;
    private LinkedList<Point> snake = new LinkedList<>();

    /**
     * Initialize your data structure here.
     * 
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
     *               first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        rows = height;
        cols = width;
        foods = food;
        // starts at (0, 0)
        snake.add(new Point(0, 0));
        len = 0;
    }

    /**
     * Moves the snake.
     * 
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over. Game over
     *         when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        Point curr = new Point(snake.get(0).x, snake.get(0).y);

        switch (direction) {
            case "U":
                curr.x--;
                break;
            case "L":
                curr.y--;
                break;
            case "R":
                curr.y++;
                break;
            case "D":
                curr.x++;
                break;
            default:
                break;
        }

        if (curr.x < 0 || curr.x >= rows || curr.y < 0 || curr.y >= cols)
            return -1;

        for (int i = 1; i < snake.size() - 1; i++) {
            if (curr.equals(snake.get(i))) {
                return -1;
            }
        }

        snake.addFirst(curr);

        // handle eating food logic
        if (len < foods.length) {
            // Each food appears one by one on the screen. For example,
            // the second food will not appear until the first food was eaten by the snake.
            Point food = new Point(foods[len][0], foods[len][1]);
            if (food.equals(curr)) {
                len++;
            }
        }

        // to accomplish moving forward, we need to remove the last
        if (snake.size() > len + 1) {
            snake.removeLast();
        }

        return len;

    }

    private static class Point {
        int x;
        int y;

        Point(int inputX, int inputY) {
            x = inputX;
            y = inputY;
        }

        boolean equals(Point pt) {
            return x == pt.x && y == pt.y;
        }
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such: SnakeGame obj
 * = new SnakeGame(width, height, food); int param_1 = obj.move(direction);
 */
```