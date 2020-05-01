Common questions for HashMap:
1. Java7和Java8的区别？
2. 默认初始化大小是多少？为啥是这么多？为啥大小都是2的幂？
3. HashMap的扩容方式？负载因子是多少？为什是这么多？
4. HashMap的主要参数都有哪些？
5. HashMap是怎么处理hash碰撞的？
6. Hash的计算规则？
7. [How does put() method of HashMap works in Java?](https://www.java67.com/2013/06/how-get-method-of-hashmap-or-hashtable-works-internally.html)
8.  [What is the requirement for an object to be used as key or value in HashMap?](http://javarevisited.blogspot.com//2013/08/10-equals-and-hashcode-interview.html)
9.  [What will happen if you try to store a key which is already present in HashMap?](http://www.java67.com/2013/02/10-examples-of-hashmap-in-java-programming-tutorial.html)
10. [Can you store a null key in Java HashMap?](http://javarevisited.blogspot.sg/2011/02/how-hashmap-works-in-java.html)
11. [Can you store a null value inside HashMap in Java?](http://www.java67.com/2016/08/difference-between-hashmap-and-IdentityHashMap-in-java.html)
12. [How does HashMap handle collisions in Java?](http://javarevisited.blogspot.sg/2016/01/how-does-java-hashmap-or-linkedhahsmap-handles.html)
13. [What are the different ways to iterate over HashMap in Java?](https://javarevisited.blogspot.sg/2011/12/how-to-traverse-or-loop-hashmap-in-java.html)
14. [How do you remove a mapping while iterating over HashMap in Java?](http://www.java67.com/2017/06/how-to-remove-entry-keyvalue-from-HashMap-in-java.html)
15. [Can you sort HashMap in Java?](https://javarevisited.blogspot.com/2017/09/java-8-sorting-hashmap-by-values-in.html)
16. [What is the load factor in HashMap?](http://www.java67.com/2014/07/21-frequently-asked-java-interview-questions-answers.html)
17. [How does resizing happens in HashMap?](http://www.java67.com/2012/09/top-10-tricky-java-interview-questions-answers.html)
18. How many entries you can store in HashMap? What is the maximum limit?<br>
    There is no maximum limit for HashMap, you can store as many entries as you want because when you run out of the bucket, entries will be added to a linked list which can support an infinite number of entries, of course until you exhaust all the memory you have.

    Btw, the size() method of HashMap return an int, which has a limit, once a number of entries cross the limit, size() will overflow, and if your program relies on that, then it will break.

    This issue has been addressed in JDK 8 by introducing a new method called mappingCount(), which returns a long value. So, you should use mappingCount() for large maps. See Java SE 8 for Really Impatient to learn more about new methods introduced in existing interfaces in JDK 8.

Read more: https://www.java67.com/2017/08/top-10-java-hashmap-interview-questions.html#ixzz6L7Fv1VEp

23. [How you will design a good key for HashMap?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#1)
24. [Difference between HashMap and ConcurrentHashMap?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#2)
25. [Difference between HashMap and Collections.synchronizedMap(HashMap)?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#3)
26. [Difference between ConcurrentHashMap and Collections.synchronizedMap(HashMap)?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#4)
27. [Difference between HashMap and HashTable?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#5)
28. [Difference between HashTable and Collections.synchronized(HashMap)?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#5)
29. [Impact of random/fixed hashCode() value for key?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#6)
30. [Using HashMap in non-synchronized code in multi-threaded application?](https://howtodoinjava.com/interview-questions/hashmap-concurrenthashmap-interview-questions/#7)



From:
1. https://zhuanlan.zhihu.com/p/96426441
2. https://zhuanlan.zhihu.com/p/79219960
3. https://www.java67.com/2017/08/top-10-java-hashmap-interview-questions.html
4. https://javarevisited.blogspot.com/2013/08/10-equals-and-hashcode-interview.html#axzz6LC0hscPJ