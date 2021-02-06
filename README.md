# 8puzzleAstar
Solving an N by N 8 puzzle using A Star search agorithm, priority queue and a tree.

Input: N = board size

g(n) = path depth 
h(n) = heuristic counts the number of tiles out of place

The best nodes are stored and sorted in a priority queue based on cost = g(n) + h(n)

The algorithm converges aggressively on a pretty good solution, but can get stuck in a loop especially with large values of n, lots of room for improvement.


