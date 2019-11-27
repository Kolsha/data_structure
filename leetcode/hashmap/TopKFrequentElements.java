package exam.review.leetcode.hashmap;

import java.util.*;

/**
 * Created by shanwu on 17-1-15.
 */
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        final int size = nums.length;
        for(int i =0;i<size;i++) {
            int temp = nums[i];
            Object val = map.get(temp);
            if(val == null) {
                map.put(temp, 1);
            } else {
                int num = (int) val;
                map.put(temp, ++num);
            }
        }

        ArrayList<FreqNum> list = new ArrayList();
        for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
            list.add(FreqNum.valueOf(entry.getKey(), entry.getValue()));
        }

        Collections.sort(list);

        ArrayList<Integer> result = new ArrayList();
        for(int i = 0; i < k; i++ ) {
            result.add(list.get(i).getVal());
        }

        return result;

    }

    private static class FreqNum implements Comparable<FreqNum> {
        final int val;
        final int count;

        private FreqNum(int v, int c) {
            val = v;
            count = c;
        }

        public static FreqNum valueOf(int v, int c) {
            return new FreqNum(v,c);
        }

        public int getVal() {
            return val;
        }

        @Override
        public int compareTo(FreqNum o) {
            return o.count - count;
        }
    }
}
