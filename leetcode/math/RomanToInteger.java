package exam.review.leetcode.math;

/**
 * Created by shanwu on 17-1-15.
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        final int size = s.length();
        int[] buf = new int[size];
        for(int i = 0; i < size; i++) {
            char temp = s.charAt(i);
            buf[i] = getIntFromRom(temp);
        }

        int sum = 0;
        for(int i = 0; i < size -1; i++) {
            if(buf[i] < buf[i+1]) {
                sum = sum - buf[i];
            } else {
                sum = sum + buf[i];
            }
        }
        return sum+buf[buf.length-1];
    }

    private int getIntFromRom(char a) {
        switch (a){
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X' :
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return -1;
        }
    }
}
