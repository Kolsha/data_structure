From:
https://web.stanford.edu/class/cs97si/02-mathematics.pdf

Outline
===
#### Algebra
1. <a class="nav-link" href="#sum_of_powers">Sum of powers</a>
1. <a class="nav-link" href="#fast_exp">Fast Exponentiation</a>
2. <a class="nav-link" href="#linear_algebra">Linear Algebra</a> 

#### Number Theory
1. <a class="nav-link" href="#gcd">Greatest Common Divisor (GCD)</a>
2. <a class="nav-link" href="#con&mod">Congruence & Modulo Operation</a>
3. <a class="nav-link" href="#multi_inverse">Multiplicative Inverse</a>
4. <a class="nav-link" href="#ext_eucli_algo">Extended Euclidean Algorithm</a>
5. <a class="nav-link" href="#ch_remainder_theorem">Chinese Remainder Theorem</a>


#### Combinatorics
1.  <a class="nav-link" href="#binomial_coefficients">Binomial Coefficients</a>
2.  <a class="nav-link" href="#fib_seq">Fibonacci Sequence</a>



#### Geometry


Algebra
===
<h4 id="sum_of_powers">Sum of Powers</h4>

1. $\sum_{k=1}^{n} {k} = (1 + n)*n*\frac{1} {2}$

2. $\sum_{k=1}^n {k^2} = \frac{1}{6}*(n+1)(2n+1)$

3. $\sum_{k=1}^{n} {k^3} = (\sum_{k=1}^{n} {k})^2$

<h4 id="fast_exp">Fast Exponentiation</h4>

1. [leetcode 例题: 计算 x 的 n 次方](power_of_x/pow.md)

<h4 id="linear_algebra">Linear Algebra</h4>
// todo add coding problem example for each sub-types.

1. Solve a system of linear equations.
2. Invert a matrix.
3. Find the rank of a matrix.
4. Compute the determinant of a matrix
5. All of the above can be done w/ Gaussian elimination.


Number Theory
===
<h4 id="gcd">Greatest Common Divisor (GCD)</h4>

1. [Euclidean Algorithm](https://en.wikipedia.org/wiki/Euclidean_algorithm)
2. [leetcode 例题: GCD of Strings](./gcd/gcd_of_strings.md)

<h4 id="con&mod">Congruence & Modulo Operation</h4>

1. x ≡ y (mod n) means x and y have the same remainder when divided by n.
2. Multiplicative inverse
    + x<sup>−1</sup> is the inverse of x modulo n if xx<sup>−1</sup> ≡ 1 (mod n)
    + 5<sup>−1</sup> ≡ 3 (mod 7) because 5 * 3 ≡ 15 ≡ 1 (mod 7) 
    + May not exist (e.g., inverse of 2 mod 4)
    + Exists if and only if gcd(x, n) = 1
<h4 id="multi_inverse">Multiplicative Inverse</h4>

1. All intermediate numbers computed by Euclidean algorithm
are integer combinations of a and b
    - Therefore, gcd(a, b) = ax + by for some integers x, y
    - If gcd(a, n) = 1, then ax + ny = 1 for some x, y
    - Taking modulo n gives ax ≡ 1 (mod n)

2. We will be done if we can find such x and y

<h4 id="ext_eucli_algo">Extended Euclidean Algorithm</h4>

1.  Main idea: keep the original algorithm, but write all
intermediate numbers as integer combinations of a and b

2. Exercise: implementation! // todo

<h4 id="ch_remainder_theorem">Chinese Remainder Theorem</h4>

- Given a, b, m, n with gcd(m, n) = 1. Find x with x ≡ a (mod m) and x ≡ b (mod n)
   <br/>**Solution:**<br/>
   1. Let n−1 be the inverse of n modulo m.
   2. Let m−1 be the inverse of m modulo n.
   3. Set x = ann<sup>−1</sup> + bmm<sup>−1</sup> (check this yourself)
   
- Extension: solving for more simultaneous equations

Combinatorics
===
<h4 id="binomial_coefficients">Binomial Coefficients</h4>

1. ${n\choose k}$ is the number of ways to choose k objects out of n
distinguishable objects.

2. same as the coefficient of x<sup>
k</sup>y<sup>n−k</sup>
in the expansion of
(x + y)<sup>n</sup>. Hence the name “binomial coefficients”

<h4 id="comp_binomial_coefficients">Computing Binomial Coefficients</h4>
// todo add more examples and solutions

1. Solution 1: Compute using the following formula:<br/>
${n \choose k}$ = $\frac{n(n-1)...(n-k+1)}{k!}$

2. Solution 2: Pascal's triangle.

<h4 id="fib_seq">Fibonacci Sequence</h4>

 1. Definition
    - F<sub>0</sub> = 0, F<sub>1</sub> = 1, F<sub>n</sub> = F<sub>n-1</sub>+F<sub>n-2</sub>, where n>=2.
2. Example --> todo

<h4 id="closed_form">Closed Form</h4>
