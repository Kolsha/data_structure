
1. Instead of using `%` mod operator, try to use bit manipulation to improve performance.

   ```java
   public boolean canWinNim(int n) {
       return (n & 3) != 0; // n & 3 equals to n % 4
   }
   ```
   
   There is only a simple way to find modulo of `2^i` numbers using bitwise.

   There is an ingenious way to solve [Mersenne number](https://mathworld.wolfram.com/MersenneNumber.html) cases as per [the link](http://homepage.cs.uiowa.edu/~jones/bcd/mod.shtml) such as n % 3, n % 7... There are special cases for n % 5, n % 255, and composite cases such as n % 6.

   For cases 2^i, ( 2, 4, 8, 16 ...):

   `n % 2^i = n & (2^i - 1)`
   

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
10. [binary search](https://leetcode.com/problems/first-bad-version/discuss/71296/O(lgN)-simple-Java-solution/73485) 
11. https://leetcode.com/problems/first-bad-version/discuss/71296/O(lgN)-simple-Java-solution/218530
12. Most common 75 questions: https://leetcode.com/list?selectedList=x4smwolg


ref: https://leetcode.com/discuss/interview-experience/680549/google-amazon-l3l4-sde2-june-2020-pending-offer


13. 檢查字符串內是否有特定子字符串: String.contains()
```java
String string = "Java";
String substring = "va";

System.out.println(string.contains(substring));
```

output
```bash
true
```

14. 给出第一子字符串的 Index: String.indexOf()


```java
indexOf(int ch)
indexOf(int ch, int fromIndex)
indexOf(String str)
indexOf(String str, int fromIndex)
```

example
```java
String string = "Lorem ipsum dolor sit amet.";

// You can also use unicode for characters
System.out.println(string.indexOf('i'));
System.out.println(string.indexOf('i', 8));
System.out.println(string.indexOf("dolor"));
System.out.println(string.indexOf("Lorem", 10));
```
output
```bash
6
19
12
-1
```

15. 给出最后目标子字符串的 Index : <b>String.lastIndexOf()</b>
```java
String string = "Lorem ipsum dolor sit amet.";

// You can also use unicode for characters
System.out.println(string.lastIndexOf('i'));
System.out.println(string.lastIndexOf('i', 8));
System.out.println(string.lastIndexOf("dolor"));
System.out.println(string.lastIndexOf("Lorem", 10));
```

output
```bash
19
6
12
0
```

16. how to print all the sub string




