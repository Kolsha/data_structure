### [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/)


Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:
```
int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
```

Note:
- You may assume that nums' length ≥ k-1 and k ≥ 1.

Solution
```
The class accepts an integer k while initializing. Every time you add an element into this class, it will returns the kth largest element. Using the Example in the problem description:
At beginning, we use k = 3 with an array [4, 5, 8, 2].
After kthLargest.add(3);, all the elements inside this instance is [4, 5, 8, 2, 3], and it return the kth (which is 3rd) largest element, so it returns 4.
After kthLargest.add(5);, all the elements inside this instance is [4, 5, 8, 2, 3, 5], and it return the kth (which is 3rd) largest element, so it returns 5.
After kthLargest.add(10);, all the elements inside this instance is [4, 5, 8, 2, 3, 5, 10], and it return the kth (which is 3rd) largest element, so it returns 5.
After kthLargest.add(9);, all the elements inside this instance is [4, 5, 8, 2, 3, 5, 10, 9], and it return the kth (which is 3rd) largest element, so it returns 8.
After kthLargest.add(4);, all the elements inside this instance is [4, 5, 8, 2, 3, 5, 10, 9, 4], and it return the kth (which is 3rd) largest element, so it returns 8.
```
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Comparator<Integer> comp = new Comparator<>() {
        //     @Override
        //     public int compare(Integer a, Integer b) {
        //         return a - b;
        //     }
        // };
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        
        for(int num: nums) {
            queue.offer(num);
            if(queue.size() > k) {
                queue.poll();
            }
        }
        
        return queue.poll();
    }
}
```