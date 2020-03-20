
1. Replace `%` operator with bit manipulation

```java
public boolean canWinNim(int n) {
    return (n & 3) != 0; // equals to n % 4 != 0
}
```
2. In-order traversal in BST => sorted list with ascending value

3. edge case: overflow issue
4. substring usage
5. PriorityQueue
6. Comparator/ lambda comparator
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
```java
Comparator<int[]> comp = new Comparator<>() {
            @Override
            public int compare(int[] small, int[] large) {
                // return (large[0]*large[0]+large[1]*large[1]) - (small[0]*small[0]+small[1]*small[1]); // large -> small
                return (small[0]*small[0]+small[1]*small[1]) - (large[0]*large[0]+large[1]*large[1]); // small -> large
            }
        };
```
7. ArrayList to Array
8. StringBuilder implementation, append, insert, complexity https://stackoverflow.com/questions/26170180/complexity-of-insert0-c-operation-on-stringbuffer-is-it-o1
9. Recursive & non-recursive approach
