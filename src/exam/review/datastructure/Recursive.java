package exam.review.datastructure;

/**
 * Created by shanwu on 16-11-29.
 */
public class Recursive {

    public static int getFactorial(int n) {
        if (n == 0) return 1;
        return n * getFactorial(n - 1);
    }

    public static int getNthFibnacci(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            return getNthFibnacci(n-1) + getNthFibnacci(n-2);
        }
    }

    public static void main(String[] args) {
        int target = 5;
        System.out.println("factorial: "+target+"! = "+getFactorial(target));

        target = 10;
        System.out.println("Fibonacci: "+target+"th number = "+getNthFibnacci(target));

    }
}
