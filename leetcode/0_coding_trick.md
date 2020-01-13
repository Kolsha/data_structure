
1. Replace `%` operator with bit manipulation

```java
public boolean canWinNim(int n) {
    return (n & 3) != 0; // equals to n % 4 != 0
}
```
2. In-order traversal in BST => sorted list with ascending value



