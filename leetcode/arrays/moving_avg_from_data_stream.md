### 346. Moving Average from Data Stream
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:
```
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
```

Solution:
```java
class MovingAverage {
    private int[] dataHolder = null;
    private int cur = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        dataHolder = new int[size];
    }

    public double next(int val) {
        if (dataHolder.length == 0) {
            return 0;
        }

        // full
        if (cur == dataHolder.length) {
            for (int i = 1; i < dataHolder.length; i++) {
                dataHolder[i - 1] = dataHolder[i];
            }
            dataHolder[cur - 1] = val;
        } else {
            dataHolder[cur++] = val;
        }

        double sum = 0;
        for (int i = 0; i < dataHolder.length; i++) {
            sum += dataHolder[i];
        }

        return sum / cur;
    }
}
```
 