package exam.review.leetcode.easy;

/**
 * Created by shanwu on 16-12-31.
 */
public class NumberofSegments {
    public int countSegments(String s) {
        int segCount = 0;
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            boolean isSeg = (i==0 || s.charAt(i-1) == ' ');
            if(temp != ' ' && isSeg) {
                segCount++;
            }
        }
        return segCount;
    }
}
