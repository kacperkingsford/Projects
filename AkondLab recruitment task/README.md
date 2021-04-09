## Recruitment task from AkondLab

In the programming language of your choice, write function/procedure/class for finding closest neibours.
#### Input Parameters:

-   `A`: list of `float` numbers.
-   `B`: reference list of `float` numbers.

#### Returns:

-   `neigbors`: list of `int`, length equal to `A`. The value of `neigbors[i]` is the index of the element from list `B` whose value is closest to `A[i]` among all elements of `B`.
-   (optional) `distances`: list of `float`, length equal to `A`. The value of `distances[i]` is the difference between value `A[i]` and value of it's _closest neighbor_ in `B` i. e. `distances[i] == abs(A[i] - B[neigbors[i]])`.

### Optional extensions

-   Option for skipping identical elements. When set, the "closest neighbor" is defined as element form `B` of closest but not identical value.
-   Like previous one, but with float parameter `epsilon`, and "identical" value is defined not by equality (problematic with floats) but value different by less than `epsilon`.
-   Parameter `n` for finding `n`-th closest neighbor.
---
# My implementation specification:
## Input:
- length of A
-  elements of A
- length of B
- elements of B
## Output:
- neigbors list
- distances list

## Time complexity:
### O(logm * max(n,m)) where n = length(A), m = length(B)


