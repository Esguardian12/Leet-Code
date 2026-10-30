package MinScore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/* 2492. Minimum Score of a Path Between Two Cities
 * You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.

The score of a path between two cities is defined as the minimum distance of a road in this path.

Return the minimum possible score of a path between cities 1 and n.

Note:

A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n.
 

Example 1:


Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
Output: 5
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
Example 2:


Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
Output: 2
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
 

Constraints:

2 <= n <= 105
1 <= roads.length <= 105
roads[i].length == 3
1 <= ai, bi <= n
ai != bi
1 <= distancei <= 104
There are no repeated edges.
There is at least one path between 1 and n.
 *
 * Time Complexity: O(n + E), where $n$ is the number of cities and $E$ is the number of roads. You visit each node and evaluate each edge in the 
 * 			connected component a constant number of times.
 * Space Complexity: O(n + E), to store the graph in an adjacency list, plus $O(n)$ for the BFS queue and the visited array. 
 */

public class MinScore {
	
	public int minScore(int n, int[][] roads) {
		// Build the adjacency list for our graph
        // Each node points to a list of arrays: [neList<A>or_city, distance]
		List<List<int[]>> graph = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int[] road : roads) {
			int u = road[0];
			int v = road[1];
			int distance = road[2];
			graph.get(u).add(new int[]{v, distance});
			graph.get(v).add(new int[]{u, distance});
		}
		
		// Use a queue for BFS and a boolean array to track visited cities
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		
		// Start BFS from city 1
		queue.offer(1);
		visited[1] = true;
		
		int minScore = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			//Check all neighboring cities
			for(int[] edge : graph.get(curr)) {
				int nextCity = edge[0];
				int edgeWeight = edge[1];
				
				// We evaluate the edge weight even if the next city has been visited,
                // because we want the minimum of ALL roads in this connected component.
				minScore = Math.min(minScore, edgeWeight);
				
				// Only add unvisited cities to the queue to prevent infinite loops
				if(!visited[nextCity]) {
					visited[nextCity] = true;
					queue.offer(nextCity);
				}
			}
		}
		
		return minScore;
	}

	public static void main(String[] args) {
        MinScore solver = new MinScore();

        // Test Case 1
        int n1 = 4;
        int[][] roads1 = {
            {1, 2, 9}, 
            {2, 3, 6}, 
            {2, 4, 5}, 
            {1, 4, 7}
        };
        System.out.println("Test Case 1 Output: " + solver.minScore(n1, roads1)); 
        // Expected Output: 5

        // Test Case 2
        int n2 = 4;
        int[][] roads2 = {
            {1, 2, 2}, 
            {1, 3, 4}, 
            {3, 4, 7}
        };
        System.out.println("Test Case 2 Output: " + solver.minScore(n2, roads2)); 
        // Expected Output: 2
    }
}
