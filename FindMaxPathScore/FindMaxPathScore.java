package FindMaxPathScore;

import java.util.*;

/* 3620. Network Recovery Pathways
 * You are given a directed acyclic graph of n nodes numbered from 0 to n − 1. This is represented by a 2D array edges of length m, where edges[i] = [ui, vi, costi] indicates a one‑way communication from node ui to node vi with a recovery cost of costi.

Some nodes may be offline. You are given a boolean array online where online[i] = true means node i is online. Nodes 0 and n − 1 are always online.

A path from 0 to n − 1 is valid if:

All intermediate nodes on the path are online.
The total recovery cost of all edges on the path does not exceed k.
For each valid path, define its score as the minimum edge‑cost along that path.

Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path exists, return -1.

 

Example 1:

Input: edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10

Output: 3

Explanation:



The graph has two possible routes from node 0 to node 3:

Path 0 → 1 → 3

Total cost = 5 + 10 = 15, which exceeds k (15 > 10), so this path is invalid.

Path 0 → 2 → 3

Total cost = 3 + 4 = 7 <= k, so this path is valid.

The minimum edge‐cost along this path is min(3, 4) = 3.

There are no other valid paths. Hence, the maximum among all valid path‐scores is 3.

Example 2:

Input: edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12

Output: 6

Explanation:



Node 3 is offline, so any path passing through 3 is invalid.

Consider the remaining routes from 0 to 4:

Path 0 → 1 → 4

Total cost = 7 + 5 = 12 <= k, so this path is valid.

The minimum edge‐cost along this path is min(7, 5) = 5.

Path 0 → 2 → 3 → 4

Node 3 is offline, so this path is invalid regardless of cost.

Path 0 → 2 → 4

Total cost = 6 + 6 = 12 <= k, so this path is valid.

The minimum edge‐cost along this path is min(6, 6) = 6.

Among the two valid paths, their scores are 5 and 6. Therefore, the answer is 6.

 

Constraints:

n == online.length
2 <= n <= 5 * 104
0 <= m == edges.length <= min(105, n * (n - 1) / 2)
edges[i] = [ui, vi, costi]
0 <= ui, vi < n
ui != vi
0 <= costi <= 109
0 <= k <= 5 * 1013
online[i] is either true or false, and both online[0] and online[n − 1] are true.
The given graph is a directed acyclic graph.
 * 
 * Time Complexity: O((n + m) log m), Dominated by repeated feasibility checks during binary search.
 * Space Complexity: O(n + m)
 */

public class FindMaxPathScore {
	
	public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
		int n = online.length;
		int m = edges.length;
		
		// Build adjacency list
		List<long[]>[] adj = new List[n];
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
		int[] indeg = new int[n];
		for(int[] e : edges) {
			adj[e[0]].add(new long[] {e[1], e[2]});
			indeg[e[1]]++;
		}
		
		// Topological order (Kahn's algorithm) - graph structure is fixed
		int[] topo = new int[n];
		int idx = 0;
		Deque<Integer> queue = new ArrayDeque<>();
		int[] indegCopy = indeg.clone();
		for(int i = 0; i < n; i++) if (indegCopy[i] == 0) queue.add(i);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			topo[idx++] = u;
			for(long[] edge : adj[u]) {
				int v = (int) edge[0];
				if(--indegCopy[v] == 0) queue.add(v);
			}
		}
		// idx should equal n since graph is a DAG
		
		// Sorted distinct edge costs as binary search candidates
		long[] costs = new long[m];
		for(int i = 0; i < m; i++) costs[i] = edges[i][2];
		Arrays.sort(costs);
		int uniqueLen = 0;
		for(int i = 0; i < m; i++) {
			if(i == 0 || costs[i] != costs[uniqueLen - 1]) costs[uniqueLen++] = costs[i];
		}
		
		long lo = 0, hi = uniqueLen - 1;
		long ans = -1;
		while(lo <= hi) {
			long mid = (lo + hi) / 2;
			long T = costs[(int) mid];
			if(feasible(n, topo, adj, online, T, k)) {
				ans = T;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		
		return (int) ans;
	}

	private boolean feasible(int n, int[] topo, List<long[]>[] adj, boolean[] online, long T, long k) {
		final long INF = Long.MAX_VALUE / 2;
		long[] dist = new long[n];
		Arrays.fill(dist,  INF);
		dist[0] = 0;
		
		for(int u : topo) {
			if(dist[u] >= INF) continue;
			// Skip relaxing through offline intermediate nodes
			if(u != 0 && u != n - 1 && !online[u]) continue;
			for(long[] edge: adj[u]) {
				int v = (int) edge[0];
				long cost = edge[1];
				if (cost < T) continue; // edge filtered out for this threshold
				long nd = dist[u] + cost;
				if(nd < dist[v]) dist[v] = nd;
			}
		}
		return dist[n - 1] <= k;
	}
	
	 // ---------------- Test harness ----------------
    public static void main(String[] args) {
        FindMaxPathScore sol = new FindMaxPathScore();

        // Example 1
        int[][] edges1 = {{0,1,5},{1,3,10},{0,2,3},{2,3,4}};
        boolean[] online1 = {true,true,true,true};
        long k1 = 10;
        int result1 = sol.findMaxPathScore(edges1, online1, k1);
        System.out.println("Test 1: expected = 3, got = " + result1);

        // Example 2
        int[][] edges2 = {{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}};
        boolean[] online2 = {true,true,true,false,true};
        long k2 = 12;
        int result2 = sol.findMaxPathScore(edges2, online2, k2);
        System.out.println("Test 2: expected = 6, got = " + result2);

        // Edge case: no valid path (all paths blocked by offline nodes)
        int[][] edges3 = {{0,1,5},{1,2,5}};
        boolean[] online3 = {true, false, true};
        long k3 = 100;
        int result3 = sol.findMaxPathScore(edges3, online3, k3);
        System.out.println("Test 3 (blocked node): expected = -1, got = " + result3);

        // Edge case: k too small for any path
        int[][] edges4 = {{0,1,5},{1,2,5}};
        boolean[] online4 = {true, true, true};
        long k4 = 1;
        int result4 = sol.findMaxPathScore(edges4, online4, k4);
        System.out.println("Test 4 (k too small): expected = -1, got = " + result4);

        // Edge case: direct edge only
        int[][] edges5 = {{0,1,50}};
        boolean[] online5 = {true, true};
        long k5 = 50;
        int result5 = sol.findMaxPathScore(edges5, online5, k5);
        System.out.println("Test 5 (direct edge): expected = 50, got = " + result5);
    }
}

