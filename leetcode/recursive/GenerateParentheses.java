package exam.review.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 17-1-12.
 */
public class GenerateParentheses {
    final static char LEFT_P = '(';
    final static char RIGHT_P = ')';

    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList();
        char[] str = new char[n*2];
        getParenthesis(n,n,list,str,0);
        return list;
    }

    public void getParenthesis(int left, int right, List list, char[] str, int count) {
        if(left < 0 || right < left) { // invalid
            return;
        }

        if(left == 0 && right == 0) {
            list.add(String.valueOf(str));
        }

        if(left > 0) {
            str[count] = LEFT_P;
            getParenthesis(left-1, right, list, str, count+1);
        }

        if(right > left) {
            str[count] = RIGHT_P;
            getParenthesis(left, right-1, list, str, count+1);
        }
    }
}
