### 22. Generate Parentheses

https://leetcode.com/problems/generate-parentheses/

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

Solution

Approach 1: Backtracking

Complexity analysis:
<ul>
<li>
<p>Time Complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mfrac><msup><mn>4</mn><mi>n</mi></msup><msqrt><mi>n</mi></msqrt></mfrac><mo>)</mo></mrow><annotation encoding="application/x-tex">O(\dfrac{4^n}{\sqrt{n}})</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:2.271392em;vertical-align:-0.9300000000000002em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord"><span class="mopen nulldelimiter"></span><span class="mfrac"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:1.341392em;"><span style="top:-2.30972em;"><span class="pstrut" style="height:3em;"></span><span class="mord"><span class="mord sqrt"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:0.8002800000000001em;"><span class="svg-align" style="top:-3em;"><span class="pstrut" style="height:3em;"></span><span class="mord" style="padding-left:0.833em;"><span class="mord mathdefault">n</span></span></span><span style="top:-2.76028em;"><span class="pstrut" style="height:3em;"></span><span class="hide-tail" style="min-width:0.853em;height:1.08em;"><svg width="400em" height="1.08em" viewBox="0 0 400000 1080" preserveAspectRatio="xMinYMin slice"><path d="M95,702c-2.7,0,-7.17,-2.7,-13.5,-8c-5.8,-5.3,-9.5,
-10,-9.5,-14c0,-2,0.3,-3.3,1,-4c1.3,-2.7,23.83,-20.7,67.5,-54c44.2,-33.3,65.8,
-50.3,66.5,-51c1.3,-1.3,3,-2,5,-2c4.7,0,8.7,3.3,12,10s173,378,173,378c0.7,0,
35.3,-71,104,-213c68.7,-142,137.5,-285,206.5,-429c69,-144,104.5,-217.7,106.5,
-221c5.3,-9.3,12,-14,20,-14H400000v40H845.2724s-225.272,467,-225.272,467
s-235,486,-235,486c-2.7,4.7,-9,7,-19,7c-6,0,-10,-1,-12,-3s-194,-422,-194,-422
s-65,47,-65,47z M834 80H400000v40H845z"></path></svg></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.23972em;"><span></span></span></span></span></span></span></span><span style="top:-3.23em;"><span class="pstrut" style="height:3em;"></span><span class="frac-line" style="border-bottom-width:0.04em;"></span></span><span style="top:-3.677em;"><span class="pstrut" style="height:3em;"></span><span class="mord"><span class="mord"><span class="mord">4</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height:0.664392em;"><span style="top:-3.063em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">n</span></span></span></span></span></span></span></span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.9300000000000002em;"><span></span></span></span></span></span><span class="mclose nulldelimiter"></span></span><span class="mclose">)</span></span></span></span>.  Each valid sequence has at most <code>n</code> steps during the backtracking procedure.</p>
</li>
<li>
<p>Space Complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mfrac><msup><mn>4</mn><mi>n</mi></msup><msqrt><mi>n</mi></msqrt></mfrac><mo>)</mo></mrow><annotation encoding="application/x-tex">O(\dfrac{4^n}{\sqrt{n}})</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:2.271392em;vertical-align:-0.9300000000000002em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord"><span class="mopen nulldelimiter"></span><span class="mfrac"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:1.341392em;"><span style="top:-2.30972em;"><span class="pstrut" style="height:3em;"></span><span class="mord"><span class="mord sqrt"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height:0.8002800000000001em;"><span class="svg-align" style="top:-3em;"><span class="pstrut" style="height:3em;"></span><span class="mord" style="padding-left:0.833em;"><span class="mord mathdefault">n</span></span></span><span style="top:-2.76028em;"><span class="pstrut" style="height:3em;"></span><span class="hide-tail" style="min-width:0.853em;height:1.08em;"><svg width="400em" height="1.08em" viewBox="0 0 400000 1080" preserveAspectRatio="xMinYMin slice"><path d="M95,702c-2.7,0,-7.17,-2.7,-13.5,-8c-5.8,-5.3,-9.5,
-10,-9.5,-14c0,-2,0.3,-3.3,1,-4c1.3,-2.7,23.83,-20.7,67.5,-54c44.2,-33.3,65.8,
-50.3,66.5,-51c1.3,-1.3,3,-2,5,-2c4.7,0,8.7,3.3,12,10s173,378,173,378c0.7,0,
35.3,-71,104,-213c68.7,-142,137.5,-285,206.5,-429c69,-144,104.5,-217.7,106.5,
-221c5.3,-9.3,12,-14,20,-14H400000v40H845.2724s-225.272,467,-225.272,467
s-235,486,-235,486c-2.7,4.7,-9,7,-19,7c-6,0,-10,-1,-12,-3s-194,-422,-194,-422
s-65,47,-65,47z M834 80H400000v40H845z"></path></svg></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.23972em;"><span></span></span></span></span></span></span></span><span style="top:-3.23em;"><span class="pstrut" style="height:3em;"></span><span class="frac-line" style="border-bottom-width:0.04em;"></span></span><span style="top:-3.677em;"><span class="pstrut" style="height:3em;"></span><span class="mord"><span class="mord"><span class="mord">4</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height:0.664392em;"><span style="top:-3.063em;margin-right:0.05em;"><span class="pstrut" style="height:2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">n</span></span></span></span></span></span></span></span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height:0.9300000000000002em;"><span></span></span></span></span></span><span class="mclose nulldelimiter"></span></span><span class="mclose">)</span></span></span></span>, as described above, and using <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>O</mi><mo>(</mo><mi>n</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">O(n)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault" style="margin-right:0.02778em;">O</span><span class="mopen">(</span><span class="mord mathdefault">n</span><span class="mclose">)</span></span></span></span> space to store the sequence.
<br>
<br></p>
</li>
</ul>

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }
    
    private void backtrack(ArrayList<String> res, String temp, int open, int close, int max) {
        if(temp.length() == max * 2) {
            res.add(temp);
            return;
        }
        
        if(open < max) {
            backtrack(res, temp + '(', open + 1, close, max);
        }
        
        if (close < open) {
            backtrack(res, temp + ')', open, close + 1, max);
        }
    }
}
```