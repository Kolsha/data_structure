package exam.review.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 16-12-21.
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        ArrayList<String> list = new ArrayList();
        String temp;
        for(int i = 1; i<=n ; i++) {
            if(i%3 ==0 && i%5 ==0) {
                temp = "FizzBuzz";
            } else if(i%3 == 0) {
                temp = "Fizz";
            } else if(i%5 == 0) {
                temp = "Buzz";
            } else {
                temp = String.valueOf(i);
            }
            list.add(temp);

        }
        return list;
    }
}
