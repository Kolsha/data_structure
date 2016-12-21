package leetcode.easy;

/**
 * Created by shanwu on 16-12-21.
 */
public class ReverseString {
    public String reverseString(String s) {
        char[] charArray = s.toCharArray();
        final int size = charArray.length;
        for(int i = 0; i < size/2;i++) {
            char temp = charArray[i];
            charArray[i] = charArray[size-1-i];
            charArray[size-1-i] = temp;
        }
        return String.valueOf(charArray);
    }
}
