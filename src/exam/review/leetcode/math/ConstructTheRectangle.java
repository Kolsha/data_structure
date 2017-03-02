package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-3-2.
 */
public class ConstructTheRectangle {
    public int[] constructRectangle(int area) {
        int[] result = new int[2];
        if(area <=0) {
            return result;
        }

        int w = (int) Math.sqrt(area);

        while(area%w!=0) {
            w--;
        }

        int h = area/w;
        result[0] = h;
        result[1] = w;
        return result;
    }
}
