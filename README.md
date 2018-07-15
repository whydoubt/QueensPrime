# N-Queens Prime
  
## Problem Definition

Place N queens on an NxN chess board so that none of them attack each other
(the classic n-queens problem). Additionally, please make sure that no three
queens are in a straight line at ANY angle, so queens on A1, C2 and E3, despite
not attacking each other, form a straight line at some angle.

## Running

As an example, to find a solution where N = 4, execute

```
./gradlew run -PN=4
```

## Brainstorming

On an NxN board, with N queens, there must be EXACTLY one per row.

Starting with a N-ary tree with a depth of N, assume that each node of depth 1
represents an one occupied column in row 1.  Each depth-2 sub-node represents
one occupied column in row 2, and so on.

If the only rule was one queen per row, the number of leaves would be pow(N, N)  
|N |leaves        |
|--|--------------|
|4 |   256        |
|8 |16.777 * 10^6 |
|12| 8.916 * 10^12|
|16|18.447 * 10^18|

On an NxN board, with N queens, there must also be EXACTLY one per column.
Adding this rule, and ignoring branches where this is not followed, reduces
the number of leaves to factorial(N)
|N |leaves         |
|--|---------------|
|4 |     24        |
|8 |  40320        |
|12|479.001 * 10^6 |
|16| 20.923 * 10^12|

The two regular diagonals may be uniqely identified as the sum and the
difference of the row and column numbers.  That is, if queens sit on locations
(0, 3) and (4, 7) then they share the 'difference' diagonal of -3, and if they
sit on locatins (3, 4) and (1, 6) then they share the 'sum' diagonal of 7.
