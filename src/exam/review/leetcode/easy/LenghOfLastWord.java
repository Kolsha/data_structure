package exam.review.leetcode.easy;

/**
 * Created by shanwu on 16-12-31.
 */
public class LenghOfLastWord {
    public class Solution {
        public int lengthOfLastWord(String s) {


            int val = 0;
            for(int i = s.length() - 1; i >=0; i--) {
                if(s.charAt(i) == ' ' && val!=0) {
                    return val;
                } else if(s.charAt(i)!= ' ') {
                    val++;
                }
            }

            return val;
        }
    }
}
