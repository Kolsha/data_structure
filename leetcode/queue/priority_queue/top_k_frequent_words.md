### 692. Top K Frequent Words

https://leetcode.com/problems/top-k-frequent-words/

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
```
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
```
Example 2:
```
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
```
Note:
- You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
- Input words contain only lowercase letters.

Follow up:
- Try to solve it in O(n log k) time and O(n) extra space.


```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        Comparator<WordInfo> cmp = new Comparator<>() {
            @Override
            public int compare(WordInfo w1, WordInfo w2) {
                if (w2.count != w1.count) {
                    return w2.count - w1.count;
                } else {
                    return w1.word.compareTo(w2.word);
                }
            }
        };
        PriorityQueue<WordInfo> queue = new PriorityQueue<>(cmp);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (map.containsKey(word)) {
                queue.offer(new WordInfo(word, map.get(word)));
                map.remove(word);
            }
        }

        ArrayList<String> res = new ArrayList<>();
        while (k > 0) {
            WordInfo wordInfo = queue.poll();
            res.add(wordInfo.word);
            k--;
        }
        return res;
    }

    private static class WordInfo {
        String word;
        int count;

        public WordInfo(String iWord, int iCount) {
            word = iWord;
            count = iCount;
        }
    }
}
```