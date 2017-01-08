package exam.review.leetcode.bit_operation;

/**
 * Created by shanwu on 17-1-8.
 */
public class HammingDistance {
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int dist = 0;

        while(xor!=0) {
            int m = xor & 1;
            if(m == 1) {
                dist++;
            }
            xor = xor >> 1;

        }

        return dist;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(3,1));
    }
}
