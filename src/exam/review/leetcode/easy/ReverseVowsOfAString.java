package exam.review.leetcode.easy;

import java.util.ArrayList;

/**
 * Created by shanwu on 17-1-4.
 */
public class ReverseVowsOfAString {
    public class Solution {
        public String reverseVowels(String s) {
            char[] resultCharArray = new char[s.length()];
            ArrayList<Integer> vowIndexList = new ArrayList();
            int lastVowIndex = -1;
            for(int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if(isVow(ch)) {
                    vowIndexList.add(i);
                } else {
                    resultCharArray[i] = ch;
                }
            }

            for(int i =0; i < resultCharArray.length; i++) {
                char ch = resultCharArray[i];
                if(ch == '\u0000') {
                    resultCharArray[i] = s.charAt(vowIndexList.remove(vowIndexList.size()-1));
                }
            }
            return String.valueOf(resultCharArray);

        }

        public boolean isVow(char ch) {
            return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ) || (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' );
        }
    }
}
