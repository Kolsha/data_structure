### 326. Power of Three

https://leetcode.com/problems/power-of-three/


Given an integer, write a function to determine if it is a power of three.

**Example 1:**
```
Input: 27
Output: true
```
**Example 2:**
```
Input: 0
Output: false
```

**Example 3:**
```
Input: 9
Output: true
```

**Solution:**

Approach 1: loop iteration

Complexity analysis
- Time complexity: O(logN)
- Space complexity: O(1)

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }
}
```

Approach 2: Mathematics

We can use mathematics as follows:

<p><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>n</mi><mo>=</mo><msup><mn>3</mn><mi>i</mi></msup><mspace linebreak="newline"></mspace><mi>i</mi><mo>=</mo><msub><mi>log</mi><mo>⁡</mo><mn>3</mn></msub><mo>(</mo><mi>n</mi><mo>)</mo><mspace linebreak="newline"></mspace><mi>i</mi><mo>=</mo><mfrac><mrow><msub><mi>log</mi><mo>⁡</mo><mi>b</mi></msub><mo>(</mo><mi>n</mi><mo>)</mo></mrow><mrow><msub><mi>log</mi><mo>⁡</mo><mi>b</mi></msub><mo>(</mo><mn>3</mn><mo>)</mo></mrow></mfrac></mrow><annotation encoding="application/x-tex">
n = 3^i \\
i = \log_3(n) \\
i = \frac{\log_b(n)}{\log_b(3)}
</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.43056em;vertical-align:0em;"></span><span class="mord mathdefault">n</span><span class="mspace" style="margin-right:0.2777777777777778em;"></span><span class="mrel">=</span><span class="mspace" style="margin-right:0.2777777777777778em;"></span></span><span class="base"><span class="strut" style="height:0.824664em;vertical-align:0em;"></span><span class="mord"><span class="mord">3</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height:0.824664em;"><span style="top:-3.063em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">i</span></span></span></span></span></span></span></span></span><span class="mspace newline"></span><span class="base"><span class="strut" style="height:0.65952em;vertical-align:0em;"></span><span class="mord mathdefault">i</span><span class="mspace" style="margin-right:0.2777777777777778em;"></span><span class="mrel">=</span><span class="mspace" style="margin-right:0.2777777777777778em;"></span></span><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mop"><span class="mop">lo<span style="margin-right:0.01389em;">g</span></span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:0.20696799999999996em;"><span style="top:-2.4558600000000004em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight">3</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.24414em;"><span></span></span></span></span></span></span><span class="mopen">(</span><span class="mord mathdefault">n</span><span class="mclose">)</span></span><span class="mspace newline"></span><span class="base"><span class="strut" style="height:0.65952em;vertical-align:0em;"></span><span class="mord mathdefault">i</span><span class="mspace" style="margin-right:0.2777777777777778em;"></span><span class="mrel">=</span><span class="mspace" style="margin-right:0.2777777777777778em;"></span></span><span class="base"><span class="strut" style="height:1.5522159999999998em;vertical-align:-0.5311079999999999em;"></span><span class="mord"><span class="mopen nulldelimiter"></span><span class="mfrac"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:1.021108em;"><span style="top:-2.6550000000000002em;"><span class="pstrut" style="height:3em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight"><span class="mop mtight"><span class="mop mtight">lo<span style="margin-right:0.01389em;">g</span></span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:0.23015999999999992em;"><span style="top:-2.2341314285714287em;margin-right:0.07142857142857144em;"><span class="pstrut" style="height:2.5em;"></span><span class="sizing reset-size3 size1 mtight"><span class="mord mathdefault mtight">b</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.26586857142857145em;"><span></span></span></span></span></span></span><span class="mopen mtight">(</span><span class="mord mtight">3</span><span class="mclose mtight">)</span></span></span></span><span style="top:-3.23em;"><span class="pstrut" style="height:3em;"></span><span class="frac-line" style="border-bottom-width:0.04em;"></span></span><span style="top:-3.496108em;"><span class="pstrut" style="height:3em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight"><span class="mop mtight"><span class="mop mtight">lo<span style="margin-right:0.01389em;">g</span></span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:0.23015999999999992em;"><span style="top:-2.2341314285714287em;margin-right:0.07142857142857144em;"><span class="pstrut" style="height:2.5em;"></span><span class="sizing reset-size3 size1 mtight"><span class="mord mathdefault mtight">b</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.26586857142857145em;"><span></span></span></span></span></span></span><span class="mopen mtight">(</span><span class="mord mathdefault mtight">n</span><span class="mclose mtight">)</span></span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.5311079999999999em;"><span></span></span></span></span></span><span class="mclose nulldelimiter"></span></span></span></span></span></p>

<p><code>n</code> is a power of three if and only if <code>i</code> is an integer. In Java, we check if a number is an integer by taking the decimal part (using <code>% 1</code>) and checking if it is 0.</p>

**Common pitfalls**

<p>This solution is problematic because we start using <code>double</code>s, which means we are subject to precision errors. This means, we should never use <code>==</code> when comparing <code>double</code>s. That is because the result of <code>Math.log10(n) / Math.log10(3)</code> could be <code>5.0000001</code> or <code>4.9999999</code>. This effect can be observed by using the function <code>Math.log()</code> instead of <code>Math.log10()</code>.</p>

<p>In order to fix that, we need to compare the result against an <code>epsilon</code>.</p>

```java
return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
```

Complexity analysis:
- Time complexity: Depends on log implementation
- Space complexity: O(1)