### 855. Exam Room

https://leetcode.com/problems/exam-room/

In an exam room, there are N seats in a single row, numbered `0, 1, 2, ..., N-1`.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class `ExamRoom(int N)` that exposes two functions: `ExamRoom.seat()` returning an `int` representing what seat the student sat in, and `ExamRoom.leave(int p)` representing that the student in seat number `p` now leaves the room.  It is guaranteed that any calls to `ExamRoom.leave(p)` have a student sitting in seat `p`.

 

Example 1:
```
Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student sits at the last seat number 5.
```
​

Note:

1. 1 <= N <= 10^9
2. `ExamRoom.seat()` and `ExamRoom.leave()` will be called at most `10^4` times across all test cases.
3. Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.

Solution

Approach 1: Maintain Sorted Positions with TreeSet

We'll maintain `ExamRoom.students`, a `sorted list` (or `TreeSet` in Java) of positions the students are currently seated in.

The `ExamRoom.leave(p)` operation is clear - we will just `list.remove` (or `TreeSet.remove`) the student from `ExamRoom.students`.

Let's focus on the `ExamRoom.seat() : int` operation. For each pair of adjacent students i and j, the maximum distance to the closest student is `d = (j - i) / 2`, achieved in the left-most seat `i + d`. Otherwise, we could also sit in the left-most seat, or the right-most seat.

Finally, we should handle the case when there are no students separately.

For more details, please review the comments made in the implementations.

Complexity Analysis

<ul>
<li>
<p>Time Complexity:  Each <code>seat</code> operation is <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>P</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(P)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord mathdefault" style="margin-right: 0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right: 0.13889em;">P</span><span class="mclose">)</span></span></span></span></span>, (where <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>P</mi></mrow><annotation encoding="application/x-tex">P</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.68333em; vertical-align: 0em;"></span><span class="mord mathdefault" style="margin-right: 0.13889em;">P</span></span></span></span></span> is the number of students sitting), as we iterate through every student.  Each <code>leave</code> operation is <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>P</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(P)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord mathdefault" style="margin-right: 0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right: 0.13889em;">P</span><span class="mclose">)</span></span></span></span></span> (<span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>log</mi><mo>⁡</mo><mi>P</mi></mrow><annotation encoding="application/x-tex">\log P</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.88888em; vertical-align: -0.19444em;"></span><span class="mop">lo<span style="margin-right: 0.01389em;">g</span></span><span class="mspace" style="margin-right: 0.166667em;"></span><span class="mord mathdefault" style="margin-right: 0.13889em;">P</span></span></span></span></span> in Java).</p>
</li>
<li>
<p>Space Complexity:  <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>P</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(P)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord mathdefault" style="margin-right: 0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right: 0.13889em;">P</span><span class="mclose">)</span></span></span></span></span>, the space used to store the positions of each student sitting.</p>
</li>
</ul>
```java
class ExamRoom {
    private int totalSeats = 0;
    private TreeSet<Integer> studentPos = new TreeSet<>();

    public ExamRoom(int N) {
        totalSeats = N;
    }
    
    public int seat() {
        // Let's determine the position of the next student to sit down
        int nextPos = 0;
        if(studentPos.size() > 0) {
            int dist = studentPos.first();
            Integer prev = null;
            for(Integer s: studentPos) {
                if(prev != null) {
                    int d = (s - prev) / 2;
                    if(d > dist) {
                        dist = d;
                        nextPos = prev + dist;
                    }
                }
                prev = s;
            }
            
            if(totalSeats - 1 - studentPos.last() > dist) {
                nextPos = totalSeats - 1;
            }
        }
        studentPos.add(nextPos);
        return nextPos;
    }
    
    public void leave(int p) {
        studentPos.remove(p);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
```