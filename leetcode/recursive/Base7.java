package exam.review.leetcode.recursive;

/**
 * Created by shanwu on 17-3-1.
 */
public class Base7 {
    public String convertToBase7(int num) {
        if(num < 0) {
            return "-"+convertToBase7(-num);
        }

        if(num < 7) {
            return ""+num;
        }

        return convertToBase7(num/7) + num%7;
    }
}
