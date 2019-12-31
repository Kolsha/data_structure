
Replace `%` operator with bit manipulation

```java
public boolean canWinNim(int n) {
    return (n & 3) != 0; // equals to n % 4 != 0
}
```
