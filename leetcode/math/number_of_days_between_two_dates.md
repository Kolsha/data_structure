### 1360. Number of Days Between Two Dates

Write a program to count the number of days between two dates.

The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.

 

Example 1:
```
Input: date1 = "2019-06-29", date2 = "2019-06-30"
Output: 1
```
Example 2:
```
Input: date1 = "2020-01-15", date2 = "2019-12-31"
Output: 15
``` 

Constraints:

- The given dates are valid dates between the years 1971 and 2100.

Solution

```java
class Solution {
     public int daysBetweenDates(String date1, String date2) {
        int days1 = days(date1);
        int days2 = days(date2);
        return Math.abs(days1 - days2);
    }

    int[] mdays = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    // days from 1900-1-0
    private int days(String d) {
        String[] ss = d.split("-");
        int year = Integer.valueOf(ss[0]);
        int month = Integer.valueOf(ss[1]);
        int day = Integer.valueOf(ss[2]);
        // years we spent, hence -1 because current year is not spent yet
        int ydelta = year - 1 - 1900;
        int dy = ydelta * 365 + ydelta / 4;// from 1900 every 4 years is leap year
        // month-1, current month is not done yet
        int dm = mdays[month - 1];
        if (isleap(year) && month - 1 >= 2) {
            dm++;
        }
        return dy + dm + day;
    }

    private boolean isleap(int year) {
        return (year % 100 != 0 && year % 4 == 0) || (year % 100 == 0 && year % 400 == 0);
    }

}
```