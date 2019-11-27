package exam.review.leetcode.hashmap;

/**
 * https://leetcode.com/problems/max-points-on-a-line/description/
 */
public class MaxPointsOnALine {
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 0;
        for (int i = 0, size = points.length; i < size; i++) {
            int overlap = 0;
            int max = 0;
            map.clear();
            for (int j = i + 1; j < size; j++) {
                int deltaX = points[j].x - points[i].x;
                int deltaY = points[j].y - points[i].y;

                if (deltaX == 0 && deltaY == 0) {
                    overlap++;
                    continue;
                }

                int gcd = getGCD(deltaX, deltaY);
                if (gcd != 0) {
                    deltaX = deltaX / gcd;
                    deltaY = deltaY / gcd;
                }

                if (map.containsKey(deltaX)) {
                    if (map.get(deltaX).containsKey(deltaY)) {
                        map.get(deltaX).put(deltaY, map.get(deltaX).get(deltaY) + 1);
                    } else {
                        map.get(deltaX).put(deltaY, 1);
                    }
                } else {
                    HashMap<Integer, Integer> yMapping = new HashMap<>();
                    yMapping.put(deltaY, 1);
                    map.put(deltaX, yMapping);
                }

                max = Math.max(max, map.get(deltaX).get(deltaY));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }

    private int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
}