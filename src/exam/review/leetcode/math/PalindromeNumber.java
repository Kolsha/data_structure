package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-1-17.
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x!=0 && x%10 == 0)) {
            return false;
        }

        int rev = 0;
        while(x > rev) {
            rev = rev*10 + x%10;
            x /= 10;
        }

        return (x == rev
                || x == rev/10); // when we have odd digits

    }
}
