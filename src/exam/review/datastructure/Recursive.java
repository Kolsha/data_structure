package exam.review.datastructure;

/**
 * Created by shanwu on 16-11-29.
 */
public class Recursive {

    public static int getFactorial(int n) {
        if (n == 0) return 1;
        return n * getFactorial(n - 1);
    }

    public static void main(String[] args) {
        int target = 5;
        System.out.println("factorial: "+target+"! = "+getFactorial(target));

    }
}
