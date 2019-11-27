package exam.review.leetcode.arrays;

import java.util.Arrays;

/**
 * Created by shanwu on 17-1-4.
 */
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        if(s == null || s.length == 0) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int result = 0;
        int j = s.length -1;
        for(int i = g.length -1; i>=0; i--) {
            if(g[i] <= s[j]) {
                result++;
                j--;
                if(j < 0) {
                    return result;
                }
            }
        }

        return result;

    }
}
