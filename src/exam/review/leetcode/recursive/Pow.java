package exam.review.leetcode.recursive;

/**
 * Created by shanwu on 17-2-10.
 * PC: 1
 */
public class Pow {
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1;
        }

        double temp = myPow(x,n/2);

        if(n%2 == 0) {
            return temp * temp;
        } else {
            if(n > 0) {
                return temp*temp*x;
            } else {
                return temp*temp/x;
            }
        }
    }
}
