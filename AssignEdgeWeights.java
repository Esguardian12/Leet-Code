//package leetCode;

import java.util.ArrayList;
import java.util.List;

/* 3558. Number of Ways to Assign Edge Weights I
 * There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D integer array edges of length n - 1,
 *  where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.
 * Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.

The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.

Select any one node x at the maximum depth. Return the number of ways to assign edge weights in 
the path from node 1 to x such that its total cost is odd.

Since the answer may be large, return it modulo 109 + 7.
Note: Ignore all edges not in the path from node 1 to x.

Example 1:

Input: edges = [[1,2]]

Output: 1

Explanation:

The path from Node 1 to Node 2 consists of one edge (1 → 2).
Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
Example 2:

Input: edges = [[1,2],[1,3],[3,4],[3,5]]

Output: 2

Explanation:

The maximum depth is 2, with nodes 4 and 5 at the same depth. Either node can be selected for processing.
For example, the path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4).
Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.
 
Constraints:

2 <= n <= 105
edges.length == n - 1
edges[i] == [ui, vi]
1 <= ui, vi <= n
edges represents a valid tree.
 * 
 * Time Complexity: O(V+E), The dfs function calls itself recursively for each vertex to calculate the depth of the subtree rooted at that vertex. This
 * 			leads to a time complexity of O(E), where E is the number of edges in the graph. The assignEdgeWeight function calculates the maximum 
 * 			depth of all subtrees using the dfs function and then applies binary exponentiation to assign edge weights accordingly. The space complexity
 *          for this part remains O(V).
 * Space Complexity: O(V+E), The recursion stack during the DFS call takes up a constant amount of space, which is O(log V) in the worst case if there 
 * 			are many edges connecting each vertex.Additionally, the graph list and recursive call stack use O(E + V) space for the adjacency list 
 * 			representation of the graph and the recursion depth.
 * 
 * So, overall the time complexity is O(V+E), and the space complexity is approximately O(V) + O(log V) or O(Vlog V).
 */

public class AssignEdgeWeights {

	int MOD = 1000000007;
	
	public int dfs(List<List<Integer>> g, int x, int f) {
		int maxDep = 0;
		for (int y : g.get(x)) {
			if (y == f) continue;
			maxDep = Math.max(maxDep, dfs(g, y, x) + 1);
		}
		return maxDep;
	}
	
	public int assignEdgeWeight(int[][] edges) {
		int n = edges.length + 1;
		List<List<Integer>> g = new ArrayList<>();
		for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
		for (int[] e : edges) {
			g.get(e[0]).add(e[1]);
			g.get(e[1]).add(e[0]);
		}
		int maxDep = dfs(g, 1, 0);
		
		long ans = 1, base = 2;
		int exp = maxDep - 1;
		while (exp > 0) {
			if (exp % 2 == 1) ans = (ans * base) % MOD;
			base = (base * base) % MOD;
			exp /= 2;
		}
		return (int) ans;
	}
	
// 	public static void main(String[] args) {
//         AssignEdgeWeights obj = new AssignEdgeWeights();

//         // Example usage
//         int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
//         long result = obj.assignEdgeWeight(edges);
// //        System.out.println("Result: " + result);
// //
// //        // Calculate time and space complexity (for reference purposes)
// //        System.out.println("Time Complexity: O(V+E)"); // As there are V vertices in the graph, and E edges          //There was an error with V & E
// //        System.out.println("Space Complexity: O(V)" + ", where V is the number of vertices");
        
//         System.out.println("Test 1 - Linear chain (1-2-3-4-5): " + result);
//         System.out.println("Expected: 8");

//         System.out.println("Time Complexity: O(V + E), where V = vertices, E = edges");
//         System.out.println("Space Complexity: O(V), where V is the number of vertices");
        
//     }
	
	public static void main(String[] args) {
        AssignEdgeWeights obj = new AssignEdgeWeights();

        // Test 1: Single edge, maxDep=1, expected 2^0 = 1
        int[][] edges1 = {{1, 2}};
        System.out.println("Test 1 - Single edge (1-2): " + obj.assignEdgeWeight(edges1));
        System.out.println("Expected: 1");

        // Test 2: 3-node chain 1-2-3, maxDep=2, expected 2^1 = 2
        int[][] edges2 = {{1, 2}, {2, 3}};
        System.out.println("Test 2 - Chain (1-2-3): " + obj.assignEdgeWeight(edges2));
        System.out.println("Expected: 2");

        // Test 3: Star graph from node 1 to 2,3,4
        // maxDep = 1, expected 2^0 = 1
        int[][] edges3 = {{1, 2}, {1, 3}, {1, 4}};
        System.out.println("Test 3 - Star (1->2,3,4): " + obj.assignEdgeWeight(edges3));
        System.out.println("Expected: 1");

        // Test 4: Deeper tree 1-2-3-4-5-6-7, maxDep=6, expected 2^5 = 32
        int[][] edges4 = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
        System.out.println("Test 4 - Chain of 7 (1..7): " + obj.assignEdgeWeight(edges4));
        System.out.println("Expected: 32");

        // Test 5: Two branches, deeper one wins
        // 1-2-3-4 (depth 3) and 1-5 (depth 1), maxDep=3, expected 2^2 = 4
        int[][] edges5 = {{1,2},{2,3},{3,4},{1,5}};
        System.out.println("Test 5 - Two branches, max depth=3: " + obj.assignEdgeWeight(edges5));
        System.out.println("Expected: 4");
    }

}
