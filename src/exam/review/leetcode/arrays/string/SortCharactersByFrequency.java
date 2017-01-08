package exam.review.leetcode.arrays.string;

import java.util.*;

/**
 * Created by shanwu on 17-1-8.
 */
public class SortCharactersByFrequency {
    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap();

        final int size = s.length();
        for (int i = 0; i < size; i++) {
            char temp = s.charAt(i);
            Object val = map.get(temp);
            if (val == null) {
                map.put(temp, 1);
            } else {
                int count = (int) val;
                map.put(temp, ++count);
            }
        }

        ArrayList<Value> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            list.add(new Value(entry.getKey(), entry.getValue()));
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        for (int i = list.size()-1; i >=0; i--) {
            Value v = list.get(i);
            char temp = v.value;
            int count = v.count;
            while(count> 0) {
                sb.append(temp);
                count--;
            }
        }


        return sb.toString();

    }

    public static class Value implements Comparable {
        public char value;
        public int count;

        public Value(char c, int v) {
            value = c;
            count = v;
        }

        @Override
        public int compareTo(Object o) {
            return count - ((Value) o).count;
        }

        @Override
        public String toString() {
            return value + ":" + count;
        }
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("raaeaedere"));
    }
}
