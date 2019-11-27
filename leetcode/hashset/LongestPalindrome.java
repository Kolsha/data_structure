package exam.review.leetcode.hashset;

import java.util.HashSet;

/**
 * Created by shanwu on 17-1-13.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        HashSet<Character> set = new HashSet();
        final int size = s.length();
        for(int i = 0; i < size; i++) {
            char temp = s.charAt(i);
            if(set.contains(temp)) {
                set.remove(temp);
                count++;
            } else {
                set.add(temp);
            }
        }

        if(!set.isEmpty()) {
            return count*2+1;
        } else {
            return count*2;
        }

    }
}
