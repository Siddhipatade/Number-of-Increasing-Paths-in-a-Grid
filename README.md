# Number of Increasing Paths in a Grid

## Problem
You are given an `m` x `n` integer matrix `grid`, where you can move from a cell to any adjacent cell in all four directions. The task is to find the number of strictly increasing paths in the grid, considering that you can start from any cell and end at any cell. Since the answer may be very large, it should be returned modulo 10^9 + 7.

A strictly increasing path is a path in which the value of each cell visited is strictly greater than the value of the previous cell in the path. Two paths are considered different if they do not have the same sequence of visited cells.

## Solution
The solution uses dynamic programming to solve the problem efficiently. Here's a step-by-step explanation of the solution:

1. Initialize the necessary variables:

- `directions` array: It defines the four possible directions to move - right, left, down, and up.
- `m` and `n` represent the number of rows and columns in the grid, respectively.
- `mod` is the modulo value used to keep the answer within bounds.

2. Initialize the `dp` array:

- The `dp` array has the same dimensions as the grid and is used to store the number of strictly increasing paths for each cell.
- Initialize all cells of the `dp` array with a value of 1 because each cell can be considered as a path itself.

3. Sort the cells by their values:

- Create a `cellList` array to store the coordinates of each cell in the grid.
- Iterate through the grid and store the cell coordinates in the `cellList` array.
- Sort the `cellList` array based on the values in the corresponding cells of the grid.
- Sorting the cells ensures that we process them in increasing order of their values.

4. Iterate through the sorted cells:

- For each cell in the `cellList` array, iterate through its four neighbors.
- If a neighbor cell has a larger value than the current cell, increment the dp value of the neighbor cell by the `dp` value of the current cell.
- This is because we can form strictly increasing paths from the current cell to the neighbor cell, so we add the number of paths from the current cell to the number of paths from the neighbor cell.

5. Calculate the total number of strictly increasing paths:

- After processing all cells, sum up all the values in the `dp` array.
- Return the answer modulo `mod`.

## How to Use
To use the solution, follow these steps:

1. Provide the input matrix:

- The program will prompt you to enter the number of rows and columns for the matrix.
- Enter the elements of the matrix, row by row.
2. Output:

- The program will calculate and display the number of strictly increasing paths in the grid.
- It will also show the output paths for each cell in the grid.

## Example
Here's an example to demonstrate the usage of the solution:

Input:

```
Enter the number of rows: 3
Enter the number of columns: 3
Enter the elements of the matrix:
1 3 2
2 4 7
3 6 9
```

Output:
Number of strictly increasing paths: 9

Output Paths:

```
(0, 0) (1, 0) (2, 0) (2, 1) (2, 2) 
(0, 1) (1, 1) (1, 0) (2, 0) (2, 1) (2, 2) 
(0, 2) (1, 2) (1, 1) (2, 1) (2, 2) 
(1, 0) (2, 0) (2, 1) (2, 2) 
(1, 1) (1, 0) (2, 0) (2, 1) (2, 2) 
(1, 2) (1, 1) (2, 1) (2, 2) 
(2, 0) (2, 1) (2, 2) 
(2, 1) (2, 2) 
(2, 2)
```

In this example, the input matrix is a `3x3` grid. The solution calculates that there are `9` strictly increasing paths in the grid. It also displays the paths for each cell in the grid.

This solution provides an efficient way to solve the problem of finding the number of strictly increasing paths in a grid. The dynamic programming approach allows us to calculate the answer by iteratively updating the values in the `dp` array.


## Complexity Analysis
Let m×nm \times nm×n be the size of the input array grid.

#### Time complexity: 
`O(m⋅n⋅log⁡(m⋅n))O(m\cdot n \cdot\log(m\cdot n))O(m⋅n⋅log(m⋅n))`

We sort all cells by value, it takes O(klog⁡k)O(k\log k)O(klogk) to sort an array of size O(k)O(k)O(k), so it takes O(m⋅n⋅log⁡(m⋅n))O(m\cdot n \cdot\log(m\cdot n))O(m⋅n⋅log(m⋅n)) time.
The iteration over sorted cells has O(m⋅n)O(m \cdot n)O(m⋅n) steps, each step consists of checking at most four neighbor cells, thus it takes O(m⋅n)O(m \cdot n)O(m⋅n) time.
For initialization of dp and the calculation of answer we iterate over all the cells of the dp array, which also takes O(m⋅n)O(m \cdot n)O(m⋅n) time.
To sum up, the overall time complexity is O(m⋅n⋅log⁡(m⋅n))O(m\cdot n \cdot\log(m\cdot n))O(m⋅n⋅log(m⋅n)).
#### Space complexity: 
`O(m⋅n)O(m\cdot n)O(m⋅n)`

We used two arrays, cellList and dp, they both contain O(m⋅n)O(m \cdot n)O(m⋅n) elements.
