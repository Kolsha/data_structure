A word of advice
This problem is a companion problem to 295. Find Median From Data Stream. This means that a lot of approaches to solve this problem are based on the methods to solve 295. Find Median From Data Stream. Perhaps try that problem before you approach this one.

Solution
Approach 1: Simple Sorting
Intuition

Do what the question says.

Algorithm

Store the numbers in a window container of size kk. The following operations must take place:

1. Inserting the incoming element.
2. Deleting the outgoing element.
3. Sorting the window to find the medians.
One primitive approach is to copy kk consecutive elements from the input to the window and keep sorting these every time. This constitutes duplication of effort.

We can do a bit better if we instead insert and delete one element per window shift. The challenge then is to maintain the window as sorted, before and after the insert and delete operations.

```C++
vector<double> medianSlidingWindow(vector<int>& nums, int k)
{
    vector<double> medians;

    for (int i = 0; i + k <= nums.size(); i++) {
        vector<int> window(nums.begin() + i, nums.begin() + i + k);

        sort(window.begin(), window.end());

        if (k & 1)
            medians.push_back(window[k / 2]);
        else
            medians.push_back((double)(window[k / 2 - 1] + (double)window[k / 2]) / 2.0);
    }

    return medians;
}
```

Python comes with an excellent bisect module to help perform efficient insert operations on lists while maintaining their sorted property.

##### Complexity Analysis

Time complexity: O(n \cdot k \log k)O(n⋅klogk) to O(n \cdot k)O(n⋅k).

Copying elements into the container takes about O(k)O(k) time each. This happens about (n-k)(n−k) times.

Sorting for each of the (n-k)(n−k) sliding window instances takes about O(k \log k)O(klogk) time each.

Bisected insertion or deletion takes about O(\log k)O(logk) for searching and O(k)O(k) for actual shifting of elements. This takes place about nn times.

Space complexity: O(k)O(k) extra linear space for the window container.

##### Approach 2: Two Heaps (Lazy Removal)
Intuition

The idea is the same as Approach 3 from 295. Find Median From Data Stream. The only additional requirement is removing the outgoing elements from the window.

Since the window elements are stored in heaps, deleting elements that are not at the top of the heaps is a pain.

Some languages (like Java) provide implementations of the PriorityQueue class that allow for removing arbitrarily placed elements. Generally, using such features is not efficient nor is their portability assured.

Assuming that only the tops of heaps (and by extension the PriorityQueue class) are accessible, we need to find a way to efficiently invalidate and remove elements that are moving out of the sliding window.

At this point, an important thing to notice is the fact that if the two heaps are balanced, only the top of the heaps are actually needed to find the medians. This means that as long as we can somehow keep the heaps balanced, we could also keep some extraneous elements.

Thus, we can use hash-tables to keep track of invalidated elements. Once they reach the heap tops, we remove them from the heaps. This is the lazy removal technique.

An immediate challenge at this point is balancing the heaps while keeping extraneous elements. This is done by actually moving some elements to the heap which has extraneous elements, from the other heap. This cancels out the effect of having extraneous elements and maintains the invariant that the heaps are balanced.

NOTE: When we talk about keeping the heaps balanced, we are not referring to the actual heap sizes. We are only concerned with valid elements and hence when we talk about balancing heaps, we are referring to count of such elements.

###### Algorithm

- Two priority queues:

  1. A max-heap lo to store the smaller half of the numbers
  2. A min-heap hi to store the larger half of the numbers

- A hash-map or hash-table hash_table for keeping track of invalid numbers. It holds the count of the occurrences of all such numbers that have been invalidated and yet remain in the heaps.

- The max-heap lo is allowed to store, at worst, one more element more than the min-heap hi. Hence if we have processed kk elements:

  - If k = 2 \cdot n + 1 \quad (\forall \, n \in \mathbb{Z})k=2⋅n+1(∀n∈Z), then lo is allowed to hold n+1n+1 elements, while hi can hold nn elements.
  - If k = 2 \cdot n \quad (\forall \, n \in \mathbb{Z})k=2⋅n(∀n∈Z), then both heaps are balanced and hold nn elements each.

This gives us the nice property that when the heaps are perfectly balanced, the median can be derived from the tops of both heaps. Otherwise, the top of the max-heap lo holds the legitimate median.

NOTE: As mentioned before, when we are talking about keeping the heaps balanced, the actual sizes of the heaps are irrelevant. Only the count of valid elements in both heaps matter.

- Keep a balance factor. It indicates three situations:

  - balance = 0=0: Both heaps are balanced or nearly balanced.
  - balance < 0<0: lo needs more valid elements. Elements from hi are moved to lo.
  - balance > 0>0: hi needs more valid elements. Elements from lo are moved to hi.

- Inserting an incoming number in_num:

  - If in_num is less than or equal to the top element of lo, then it can be inserted to lo. However this unbalances hi (hi has lesser valid elements now). Hence balance is incremented.

  - Otherwise, in_num must be added to hi. Obviously, now lo is unbalanced. Hence balance is decremented.

- Lazy removal of an outgoing number out_num:

  - If out_num is present in lo, then invalidating this occurrence will unbalance lo itself. Hence balance must be decremented.

  - If out_num is present in hi, then invalidating this occurrence will unbalance hi itself. Hence balance must be incremented.

  - We increment the count of this element in the hash_table table.

  - Once an invalid element reaches either of the heap tops, we remove them and decrement their counts in the hash_table table.

```C++
vector<double> medianSlidingWindow(vector<int>& nums, int k)
{
    vector<double> medians;
    unordered_map<int, int> hash_table;
    priority_queue<int> lo;                                 // max heap
    priority_queue<int, vector<int>, greater<int> > hi;     // min heap

    int i = 0;      // index of current incoming element being processed

    // initialize the heaps
    while (i < k)
        lo.push(nums[i++]);
    for (int j = 0; j < k / 2; j++) {
        hi.push(lo.top());
        lo.pop();
    }

    while (true) {
        // get median of current window
        medians.push_back(k & 1 ? lo.top() : ((double)lo.top() + (double)hi.top()) * 0.5);

        if (i >= nums.size())
            break;                          // break if all elements processed

        int out_num = nums[i - k],          // outgoing element
            in_num = nums[i++],             // incoming element
            balance = 0;                    // balance factor

        // number `out_num` exits window
        balance += (out_num <= lo.top() ? -1 : 1);
        hash_table[out_num]++;

        // number `in_num` enters window
        if (!lo.empty() && in_num <= lo.top()) {
            balance++;
            lo.push(in_num);
        }
        else {
            balance--;
            hi.push(in_num);
        }

        // re-balance heaps
        if (balance < 0) {                  // `lo` needs more valid elements
            lo.push(hi.top());
            hi.pop();
            balance++;
        }
        if (balance > 0) {                  // `hi` needs more valid elements
            hi.push(lo.top());
            lo.pop();
            balance--;
        }

        // remove invalid numbers that should be discarded from heap tops
        while (hash_table[lo.top()]) {
            hash_table[lo.top()]--;
            lo.pop();
        }
        while (!hi.empty() && hash_table[hi.top()]) {
            hash_table[hi.top()]--;
            hi.pop();
        }
    }

    return medians;
}
```

Complexity Analysis

Time complexity: O(2 \cdot n \log k) + O(n-k) \approx O(n \log k)O(2⋅nlogk)+O(n−k)≈O(nlogk).

Either (or sometimes both) of the heaps gets every element inserted into it at least once. Collectively each of those takes about O(\log k)O(logk) time. That is nn such insertions.
About (n-k)(n−k) removals from the top of the heaps take place (the number of sliding window instances). Each of those takes about O(\log k)O(logk) time.
Hash table operations are assumed to take O(1)O(1) time each. This happens roughly the same number of times as removals from heaps take place.
Space complexity: O(k) + O(n) \approx O(n)O(k)+O(n)≈O(n) extra linear space.

The heaps collectively require O(k)O(k) space.
The hash table needs about O(n-k)O(n−k) space.

Approach 3: Two Multisets
Intuition

One can see that multisets are a great way to keep elements sorted while providing efficient access to the first and last elements. Inserting and deleting arbitrary elements are also fairly efficient operations in a multiset. (Refer to Approach 4 Intuition for 295. Find Median From Data Stream)

Thus, if the previous approach gives you too much heartburn, consider replacing the use of priority_queue with multiset.

Algorithm

Inserting or deleting an element is straight-forward. Balancing the heaps takes the same route as Approach 3 of 295. Find Median From Data Stream.

```C++
vector<double> medianSlidingWindow(vector<int>& nums, int k)
{
    vector<double> medians;
    multiset<int> lo, hi;

    for (int i = 0; i < nums.size(); i++) {
        //remove outgoing element
        if (i >= k) {
            if (nums[i - k] <= *lo.rbegin())
                lo.erase(lo.find(nums[i - k]));
            else
                hi.erase(hi.find(nums[i - k]));
        }

        // insert incoming element
        lo.insert(nums[i]);

        // balance the sets
        hi.insert(*lo.rbegin());
        lo.erase(prev(lo.end()));

        if (lo.size() < hi.size()) {
            lo.insert(*hi.begin());
            hi.erase(hi.begin());
        }

        // get median
        if (i >= k - 1) {
            medians.push_back(k & 1 ? *lo.rbegin() : ((double)(*lo.rbegin()) + (double)(*hi.begin())) * 0.5);
        }
    }

    return medians;
}
```

Complexity Analysis

Time complexity: O((n-k) \cdot 6 \cdot \log k) \approx O(n \log k)O((n−k)⋅6⋅logk)≈O(nlogk).

At worst, there are three set insertions and three set deletions from the start or end. Each of these takes about O(\log k)O(logk) time.
Finding the mean takes constant O(1)O(1) time since the start or ends of sets are directly accessible.
Each of these steps takes place about (n-k)(n−k) times (the number of sliding window instances).
Space complexity: O(k)O(k) extra linear space to hold contents of the window.


