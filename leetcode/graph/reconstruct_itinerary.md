### 332. Reconstruct Itinerary

https://leetcode.com/problems/reconstruct-itinerary/

Given a list of airline tickets represented by pairs of departure and arrival airports `[from, to]`, reconstruct the itinerary in order. All of the tickets belong to a man who departs from `JFK`. Thus, the itinerary must begin with `JFK`.

Note:

1. If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
   For example, the itinerary `["JFK", "LGA"]` has a smaller lexical order than `["JFK", "LGB"]`.
2. All airports are represented by three capital letters (IATA code).
3. You may assume all tickets form at least one valid itinerary.
4. One must use all the tickets once and only once.
Example 1:
```
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
```
Example 2:
```
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
```

Solution

Approach 1: Backtracking + Greedy
As common strategies for problems of graph traversal, we often apply the methodologies of **backtracking or greedy**. As it turns out, we can apply both of them for this problem.
~~~
Typically, backtracking is used to enumerate all possible solutions for a problem, in a trial-fail-and-fallback strategy.
~~~
At each airport, one might have several possible destinations to fly to. With backtracking, we enumerate each possible destination. We mark the choice at each iteration (i.e. trial) before we move on to the chosen destination. If the destination does not lead to a solution (i.e. fail), we would then fallback to the previous state and start another iteration of trial-fail-and-fallback cycle.
~~~
A greedy algorithm is any algorithm that follows the problem-solving heuristic of making locally optimal choice at each step, with the intent of reaching the global optimum at the end.
~~~
As suggested by its definition, a greedy algorithm does not necessarily lead to a globally optimal solution, but rather a reasonable approximation in exchange of less computing time.

Nonetheless, sometimes it is the way to produce a global optimum for certain problems. This is the case for this problem as well.

At each airport, given a list of possible destinations, while backtracking, at each step we would pick the destination greedily in lexical order, i.e. the one with the smallest lexical order would have its trial first.

With this greedy strategy, we would ensure that the final solution that we find would have the smallest lexical order, because all other solutions that have smaller lexical order have been trialed and failed during the process of backtracking.

Algorithm

Here we explain how we implement a solution for this problem, by combining the strategies of backtracking and greedy.

- As the first step, we build a graph data structure from the given input. This graph should allow us to quickly identify a list of potential destinations, given an origin. Here we adopted the hashmap (or dictionary) data structure, with each entry as `<origin, [destinations]>`.

- Then due to our greedy strategy, we then should order the destination list for each entry in lexical order. As an alternative solution, one could use `PriorityQueue` data structure in the first step to keep the list of destinations, which would maintain the order at the moment of constructing the list.

- As the final step, we kick off the backtracking traversal on the above graph, to obtain the final result.

     - At the beginning of the backtracking function, as the bottom case, we check if we have already obtained a valid itinerary.

     - Otherwise, we enumerate the next destinations in order.

     - We mark the status of visit, before and after each backtracking loop.


Complexity Analysis:
<ul>
<li>
<p>Time Complexity: <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi mathvariant="normal">∣</mi><mi>E</mi><msup><mi mathvariant="normal">∣</mi><mi>d</mi></msup><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(|E|^d)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1.09911em; vertical-align: -0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right: 0.02778em;">O</span></span><span class="mopen">(</span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord"><span class="mord">∣</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height: 0.849108em;"><span class="" style="top: -3.063em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">d</span></span></span></span></span></span></span></span><span class="mclose">)</span></span></span></span></span> where <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|E|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span></span></span></span></span> is the number of total flights and <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>d</mi></mrow><annotation encoding="application/x-tex">d</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.69444em; vertical-align: 0em;"></span><span class="mord mathdefault">d</span></span></span></span></span> is the maximum number of flights from an airport.</p>
<ul>
<li>
<p>It is tricky to estimate the time complexity of the backtracking algorithm, since the algorithm often has an early stopping depending on the input.</p>
</li>
<li>
<p>To calculate a loose upper bound for the time complexity, let us consider it as a combination problem where the goal is to construct a sequence of a specific order, <em>i.e.</em> <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><msub><mi>V</mi><mn>1</mn></msub><msub><mi>V</mi><mn>2</mn></msub><mi mathvariant="normal">.</mi><mi mathvariant="normal">.</mi><mi mathvariant="normal">.</mi><msub><mi>V</mi><mi>n</mi></msub><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|V_1V_2...V_n|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord"><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height: 0.301108em;"><span class="" style="top: -2.55em; margin-left: -0.22222em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight">1</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height: 0.15em;"><span class=""></span></span></span></span></span></span><span class="mord"><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height: 0.301108em;"><span class="" style="top: -2.55em; margin-left: -0.22222em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mtight">2</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height: 0.15em;"><span class=""></span></span></span></span></span></span><span class="mord">.</span><span class="mord">.</span><span class="mord">.</span><span class="mord"><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height: 0.151392em;"><span class="" style="top: -2.55em; margin-left: -0.22222em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">n</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height: 0.15em;"><span class=""></span></span></span></span></span></span><span class="mord">∣</span></span></span></span></span>. For each position  <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><msub><mi>V</mi><mi>i</mi></msub></mrow><annotation encoding="application/x-tex">V_i</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.83333em; vertical-align: -0.15em;"></span><span class="mord"><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="msupsub"><span class="vlist-t vlist-t2"><span class="vlist-r"><span class="vlist" style="height: 0.311664em;"><span class="" style="top: -2.55em; margin-left: -0.22222em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">i</span></span></span></span><span class="vlist-s">​</span></span><span class="vlist-r"><span class="vlist" style="height: 0.15em;"><span class=""></span></span></span></span></span></span></span></span></span></span>, we could have <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>d</mi></mrow><annotation encoding="application/x-tex">d</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.69444em; vertical-align: 0em;"></span><span class="mord mathdefault">d</span></span></span></span></span> choices, <em>i.e.</em> at each airport one could have at most <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi>d</mi></mrow><annotation encoding="application/x-tex">d</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 0.69444em; vertical-align: 0em;"></span><span class="mord mathdefault">d</span></span></span></span></span> possible destinations. Since the length of the sequence is <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|E|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span></span></span></span></span>, the total number of combination would be <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>E</mi><msup><mi mathvariant="normal">∣</mi><mi>d</mi></msup></mrow><annotation encoding="application/x-tex">|E|^d</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1.09911em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord"><span class="mord">∣</span><span class="msupsub"><span class="vlist-t"><span class="vlist-r"><span class="vlist" style="height: 0.849108em;"><span class="" style="top: -3.063em; margin-right: 0.05em;"><span class="pstrut" style="height: 2.7em;"></span><span class="sizing reset-size6 size3 mtight"><span class="mord mathdefault mtight">d</span></span></span></span></span></span></span></span></span></span></span></span>.</p>
</li>
<li>
<p>In the worst case, our backtracking algorithm would have to enumerate all possible combinations.</p>
</li>
</ul>
</li>
<li>
<p>Space Complexity: <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi mathvariant="normal">∣</mi><mi>V</mi><mi mathvariant="normal">∣</mi><mo>+</mo><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(|V| + |E|)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right: 0.02778em;">O</span></span><span class="mopen">(</span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="mord">∣</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span><span class="mclose">)</span></span></span></span></span> where <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>V</mi><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|V|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="mord">∣</span></span></span></span></span> is the number of airports and <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|E|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span></span></span></span></span> is the number of flights.</p>
<ul>
<li>
<p>In the algorithm, we use the graph as well as the visit bitmap, which would require the space of <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>V</mi><mi mathvariant="normal">∣</mi><mo>+</mo><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|V| + |E|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="mord">∣</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span></span></span></span></span>.</p>
</li>
<li>
<p>Since we applied recursion in the algorithm, which would incur additional memory consumption in the function call stack. The maximum depth of the recursion would be exactly the number of flights in the input, <em>i.e.</em> <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi></mrow><annotation encoding="application/x-tex">|E|</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span></span></span></span></span>.</p>
</li>
<li>
<p>As a result, the total space complexity of the algorithm would be <span class="maths katex-rendered"><span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi mathvariant="normal">∣</mi><mi>V</mi><mi mathvariant="normal">∣</mi><mo>+</mo><mn>2</mn><mo>⋅</mo><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi><mo>)</mo><mo>=</mo><mi mathvariant="script">O</mi><mo>(</mo><mi mathvariant="normal">∣</mi><mi>V</mi><mi mathvariant="normal">∣</mi><mo>+</mo><mi mathvariant="normal">∣</mi><mi>E</mi><mi mathvariant="normal">∣</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(|V| + 2\cdot|E|) = \mathcal{O}(|V| + |E|)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right: 0.02778em;">O</span></span><span class="mopen">(</span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="mord">∣</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 0.64444em; vertical-align: 0em;"></span><span class="mord">2</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">⋅</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span><span class="mclose">)</span><span class="mspace" style="margin-right: 0.277778em;"></span><span class="mrel">=</span><span class="mspace" style="margin-right: 0.277778em;"></span></span><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right: 0.02778em;">O</span></span><span class="mopen">(</span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.22222em;">V</span><span class="mord">∣</span><span class="mspace" style="margin-right: 0.222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right: 0.222222em;"></span></span><span class="base"><span class="strut" style="height: 1em; vertical-align: -0.25em;"></span><span class="mord">∣</span><span class="mord mathdefault" style="margin-right: 0.05764em;">E</span><span class="mord">∣</span><span class="mclose">)</span></span></span></span></span>.
<br>
<br></p>
</li>
</ul>
</li>
</ul>

```java
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // 1. build graph
        HashMap<String, List<String>> graph = new HashMap<>();
        for(List<String> info: tickets) {
            String from = info.get(0);
            String to = info.get(1);
            List<String> list = graph.getOrDefault(from, new LinkedList<>());
            list.add(to);
            graph.put(from, list);
        }
        
        // 2. build visited bitmap
        HashMap<String, boolean[]> visitedMap = new HashMap<>();
        for(Map.Entry<String, List<String>> entry: graph.entrySet()) {
            // If there are multiple valid itineraries, you should return the itinerary 
            // that has the smallest lexical order when read as a single string. 
            // For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
            Collections.sort(entry.getValue());
            visitedMap.put(entry.getKey(), new boolean[entry.getValue().size()]);
        }
        
        LinkedList<String> route = new LinkedList<>();
        String origin = "JFK";
        route.add(origin);
        LinkedList<String> res = new LinkedList<>();
        backtrack(tickets, origin, graph, visitedMap, route, res);
        return res;
    }
    
    public boolean backtrack(List<List<String>> tickets, String origin, HashMap<String, List<String>> graph, HashMap<String, boolean[]> visitedMap, LinkedList<String> route, LinkedList<String> res) {
        if(route.size() == tickets.size() + 1) {
            System.out.println(route);
            res.addAll(route);
            return true;
        }

        if(!graph.containsKey(origin)) {
            return false;
        }

        // get sorted destination list
        List<String> destLists = graph.get(origin);
        boolean[] visited = visitedMap.get(origin);
        int i = 0;

        for(String dest: destLists) {
            if(!visited[i]) {
                visited[i] = true;
                route.add(dest);
                boolean isFound = backtrack(tickets, dest, graph, visitedMap, route, res);
                if(isFound) {
                    return true;
                }
                route.pollLast();
                visited[i] = false;
            }
            i++;
        }
        return false;        
    }
}
```