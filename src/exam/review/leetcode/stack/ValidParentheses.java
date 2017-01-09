package exam.review.leetcode.stack;

import java.util.Stack;

/**
 * Created by shanwu on 17-1-9.
 */
public class ValidParentheses {
    final static char LEFT1 = '(';
    final static char LEFT2 = '[';
    final static char LEFT3 = '{';

    final static char RIGHT1 = ')';
    final static char RIGHT2 = ']';
    final static char RIGHT3 = '}';

    public boolean isValid(String s) {
        final int size = s.length();
        Stack<Character> stack = new Stack();
        for(int i = 0; i < size; i++) {
            char temp = s.charAt(i);
            if(isLeftType(temp)) {
                stack.push(temp);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char top = stack.peek();
                if(isMatch(top,temp)) {
                    stack.pop();
                } else {
                    return false;
                }

            }
        }
        return  stack.isEmpty();
    }

    boolean isMatch(char l , char r) {
        return  (l == LEFT1 && r == RIGHT1) || (l == LEFT2 && r == RIGHT2) || (l == LEFT3 && r == RIGHT3);
    }

    boolean isLeftType(char t) {
        return t == LEFT1 || t == LEFT2 || t == LEFT3;
    }

}

