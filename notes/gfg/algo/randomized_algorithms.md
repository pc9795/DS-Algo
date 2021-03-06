```cmd
E(R) = r1*p1 + r2*p2 + ..... + rk*pk
Expected value of random variable = possible value * probability( possible value)
```
**Linearity of expectation**
```cmd
E(R1 + R2) = E(R1) + E(R2) <- for both dependent and independent events.
E(R1 * R2) = E(R1) * E(R2) <- for independent events.

```
**Random Variable** -> A function which maps from the set of sample space to set of real numbers.
eg.,
```cmd
X -> no of heads if a two coins are tossed
0(TT), 1(HT|TH), 2(HH)
S -> {HH, HT, TH, TT} <- sample space
X -> outcome of a dice throw
1, 2, 3, 4, 5, 6

```
**Discrete Random Variable**
 
Takes on finite number of values.
1. 0<=pi<=1
2. sum(pi)=1

**Continuous Random Variable**

takes on infinite number of values
1. 0<=f(x)<=1
2. integration(f(x)dx)=1
 
If probability of success is p in every trial, then expected number of trials until success
is 1/p.
```cmd
//Proof
E[R]=1*p+2*(1-p)*p+3(1-p)^2(p)+.....
no of trial*probability
E[R]=p[1+2*(1-p)+3(1-p)^2+....  -(1)
(1-p)E[R]=p[1(1-p)+2*(1-p)^2+3*(1-p)^3+.....  -(2)
(2)-(1)
E[R]=[1+(1-p)+(1-p)^2+.....
Infinite GP with ratio (1-p)
E[R]=1/[1-(1-p)]
E[R]=1/p

```

**Conditional Probability**

`P(A/B)=P(A intersection B)/P(B)` -> probability of event 'A' happening given that the event B happened. since B has 
already happened the sample space reduces to B.

`P(A/B)=P(B/A)P(A)/P(B)` <-bayes formula.

**Randomized Algorithms** -> An algorithm that uses random numbers to decide what to do next anywhere in tis logic.

**Monte Carlo Algorithms** -> Randomized algorithms with deterministic time complexity

**Las Vegas Algorithms** -> Randomized algorithms whose time complexity is dependent on value of random variable. To 
compute expected time taken in worst case, all possible values of the used random variable needs to be considered in 
worst case and time taken by every possible value needs to be evaluated. Average of all evaluated times is the expected 
worst case time complexity.