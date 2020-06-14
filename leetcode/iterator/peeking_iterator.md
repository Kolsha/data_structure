### 284. Peeking Iterator

https://leetcode.com/problems/peeking-iterator/

Given an Iterator class interface with methods: `next()` and `hasNext()`, design and implement a PeekingIterator that support the `peek()` operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:
```
Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
```
**Follow up**: How would you extend your design to be generic and work with all types, not just integer?

Solution

Approach 1: Saving Peeked Value
Intuition

Each time we call .next(...), a value is returned from the Iterator. If we call .next(...) again, a new value will be returned. This means that if we want to use a particular value multiple times, we had better save it.

Our .peek(...) method needs to call .next(...) on the Iterator. But because .next(...) will return a different value next time, we need to store the value we peeked so when .next(...) is called we return the correct value.

```java
import java.util.NoSuchElementException;

class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iter;
    private Integer peekedValue = null;
    
	public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
	}

	public Integer peek() {
        /* If there's not already a peeked value, get one out and store
         * it in the peekedValue variable. We aren't told what to do if
         * the iterator is actually empty -- here I have thrown an exception
         * but in an interview you should definitely ask! This is the kind of
         * thing they expect you to ask about. */
        if (peekedValue == null) {
            if (!iter.hasNext()) {
                throw new NoSuchElementException();
            }
            peekedValue = iter.next();
        }
        return peekedValue;
	}

	@Override
	public Integer next() {
        /* Firstly, we need to check if we have a value already
         * stored in the peekedValue variable. If we do, we need to
         * return it and also set peekedValue to null so that the value
         * isn't returned again. */
	    if (peekedValue != null) {
            Integer toReturn = peekedValue;
            peekedValue = null;
            return toReturn;
        }
        /* As per the Java Iterator specs, we should throw a NoSuchElementException
         * if the next element doesn't exist. */
        if (!iter.hasNext()) {
            throw new NoSuchElementException();
        }
        /* Otherwise, we need to return a new value. */
        return iter.next();
	}

	@Override
	public boolean hasNext() {
        /* If there's a value waiting in peekedValue, or if there are values
         * remaining in the iterator, we should return true. */
	    return peekedValue != null || iter.hasNext();
	}
}
```

Approach 2: Saving the Next Value
Intuition

Instead of only storing the next value after we've peeked at it, we can store it immediately in the constructor and then again in the next(...) method. This greatly simplifies the code, because we no longer need conditionals to check whether or not we are currently storing a peeked at value.

Algorithm

Note that in the Java code, we need to be careful not to cause an exception to be thrown from the constructor, in the case that the Iterator was empty at the start. We can do this by checking it has a next, and if it doesn't, then we set the next variable to null.

```java

import java.util.NoSuchElementException;

class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iter;
    private Integer next = null;
    
	public PeekingIterator(Iterator<Integer> iterator) {
        // Avoid an exception being thrown in the constructor.
        if (iterator.hasNext()) {
            next = iterator.next();
        }
        iter = iterator;
	}

	public Integer peek() {
        return next;
	}

	@Override
	public Integer next() {
        /* As per the Java Iterator specs, we should throw a NoSuchElementException
         * if the next element doesn't exist. */
	    if (next == null) {
            throw new NoSuchElementException();
        }
        Integer toReturn = next;
        next = null;
        if (iter.hasNext()) {
            next = iter.next();
        }
        return toReturn;
	}

	@Override
	public boolean hasNext() {
	    return next != null;
	}
}
```

#facebook