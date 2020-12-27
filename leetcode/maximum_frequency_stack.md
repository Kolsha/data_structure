##### Solution

##### Approach 1: Stack of Stacks
###### Intuition

Evidently, we care about the frequency of an element. Let `freq` be a Map from $x$ to the number of occurrences of $x$.

Also, we (probably) care about `maxfreq`, the current maximum frequency of any element in the stack. This is clear because we must pop the element with the maximum frequency.

The main question then becomes: among elements with the same (maximum) frequency, how do we know which element is most recent? We can use a stack to query this information: the top of the stack is the most recent.

To this end, let `group` be a map from frequency to a stack of elements with that frequency. We now have all the required components to implement `FreqStack`.

###### Algorithm

Actually, as an implementation level detail, if `x` has frequency `f`, then we'll have `x` in all `group[i] (i <= f)`, not just the top. This is because each `group[i]` will store information related to the `i`th copy of x.

Afterwards, our goal is just to maintain `freq`, `group`, and `maxfreq` as described above.


##### Complexity analysis
- Time complexity: O(1) for both push and pop operations.


- Space complexity: O(N), where N is the number of elements in the FreqStack.

```java
class FreqStack {
    Map<Integer, Integer> freq;
    Map<Integer, Stack<Integer>> group;
    int maxfreq;

    public FreqStack() {
        freq = new HashMap();
        group = new HashMap();
        maxfreq = 0;
    }

    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        if (f > maxfreq)
            maxfreq = f;

        group.computeIfAbsent(f, z-> new Stack()).push(x);
    }

    public int pop() {
        int x = group.get(maxfreq).pop();
        freq.put(x, freq.get(x) - 1);
        if (group.get(maxfreq).size() == 0)
            maxfreq--;
        return x;
    }
}
```