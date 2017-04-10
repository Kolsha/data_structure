package exam.review.leetcode.binary_search_tree;

/**
 * Created by shanwu on 17-4-10.
 */
public class GuessNumberHigherOrLower {
    static int sAns;

    public static int guessNumber(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return n;
    }

    /* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0*/
    static int guess(int num) {
        if (sAns < num) return -1;
        if (sAns > num) return 1;
        return 0;
    }

    static void setAns(int ans) {
        sAns = ans;
    }

    public static void main(String[] args) {
        setAns(6);
        System.out.println(guessNumber(10));
    }
}
