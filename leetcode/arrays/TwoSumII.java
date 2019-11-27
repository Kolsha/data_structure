package exam.review.leetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shanwu on 17-1-16.
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length == 0) {
            return null;
        }

        HashMap<Integer, List<Integer>> map = new HashMap();

        final int size = numbers.length;
        for(int i = 0; i < size; i++) {
            int temp = numbers[i];
            Object val = map.get(temp);
            if(val == null) {
                ArrayList<Integer> list = new ArrayList();
                list.add(i);
                map.put(temp,list);
            } else {
                ArrayList<Integer> list = (ArrayList<Integer>) val;
                list.add(i);
                map.put(temp,list);
            }
        }

        int[] result = new int[2];
        for(int i = 0; i < size; i++) {
            result[0] = i+1;
            int temp = numbers[i];
            int other = target - temp;
            Object val = map.get(other);
            if(val == null) {
                continue;
            } else {
                ArrayList<Integer> list = (ArrayList<Integer>) val;
                for(Integer n: list) {
                    if(n!=i) {
                        result[1] = n+1;
                        return result;
                    }
                }

            }
        }

        return null;
    }
}
