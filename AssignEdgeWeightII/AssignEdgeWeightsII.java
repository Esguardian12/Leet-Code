package AssignEdgeWeightII;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
//import java.util.Random;

/* 3559. Number of Ways to Assign Edge Weights II
 * There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.

Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.

The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.

You are given a 2D integer array queries. For each queries[i] = [ui, vi], determine the number of ways to assign weights to edges in the path such that the cost of the path between ui and vi is odd.

Return an array answer, where answer[i] is the number of valid assignments for queries[i].

Since the answer may be large, apply modulo 109 + 7 to each answer[i].

Note: For each query, disregard all edges not in the path between node ui and vi.

 

Example 1:



Input: edges = [[1,2]], queries = [[1,1],[1,2]]

Output: [0,1]

Explanation:

Query [1,1]: The path from Node 1 to itself consists of no edges, so the cost is 0. Thus, the number of valid assignments is 0.
Query [1,2]: The path from Node 1 to Node 2 consists of one edge (1 → 2). Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
Example 2:



Input: edges = [[1,2],[1,3],[3,4],[3,5]], queries = [[1,4],[3,4],[2,5]]

Output: [2,1,4]

Explanation:

Query [1,4]: The path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4). Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.
Query [3,4]: The path from Node 3 to Node 4 consists of one edge (3 → 4). Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
Query [2,5]: The path from Node 2 to Node 5 consists of three edges (2 → 1, 1 → 3, and 3 → 5). Assigning (1,2,2), (2,1,2), (2,2,1), or (1,1,1) makes the cost odd. Thus, the number of valid assignments is 4.
 

Constraints:

2 <= n <= 105
edges.length == n - 1
edges[i] == [ui, vi]
1 <= queries.length <= 105
queries[i] == [ui, vi]
1 <= ui, vi <= n
edges represents a valid tree.
 *
 * Time Complexity: 
 * Space Complexity: 
 */

//import java.util.*;

public class AssignEdgeWeightsII {

    private static final int MOD = 1_000_000_007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        int[][] up = new int[LOG][n + 1];
        int[] depth = new int[n + 1];
        Arrays.fill(depth, -1);

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        depth[1] = 0;
        up[0][1] = 1;
        while (!dq.isEmpty()) {
            int u = dq.poll();
            for (int v : g[u]) {
                if (depth[v] == -1) {
                    depth[v] = depth[u] + 1;
                    up[0][v] = u;
                    dq.add(v);
                }
            }
        }

        for (int k = 1; k < LOG; ++k)
            for (int v = 1; v <= n; ++v)
                up[k][v] = up[k - 1][up[k - 1][v]];

        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; ++i) pow2[i] = (pow2[i - 1] << 1) % MOD;

        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; ++i) {
            int u = queries[i][0], v = queries[i][1];
            int l = lca(u, v, depth, up, LOG);
            int dist = depth[u] + depth[v] - 2 * depth[l];
            ans[i] = (dist == 0) ? 0 : (int) pow2[dist - 1];
        }
        return ans;
    }

    private int lca(int a, int b, int[] depth, int[][] up, int LOG) {
        if (depth[a] < depth[b]) { int tmp = a; a = b; b = tmp; }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; ++k)
            if (((diff >> k) & 1) == 1) a = up[k][a];
        if (a == b) return a;
        for (int k = LOG - 1; k >= 0; --k)
            if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b]; }
        return up[0][a];
    }

//    // This main came from qwen2.5-code:7b in Odysseues
//    public static void main(String[] args) {
//        AssignEdgeWeightsII sol = new AssignEdgeWeightsII();
//
//        // Simple chain: 1-2-3-4-5
//        // Path (1,5) dist=4 → 2^3=8
//        // Path (2,4) dist=2 → 2^1=2
//        // Path (1,1) dist=0 → 0
//        int[][] edges = {{1,2},{2,3},{3,4},{4,5}};
//        int[][] queries = {{1,5},{2,4},{1,1},{1,3}};
//        int[] res = sol.assignEdgeWeights(edges, queries);
//        System.out.println("Test 1 (chain 1-2-3-4-5):");
//        System.out.println("  (1,5) = " + res[0] + "  expected 8");
//        System.out.println("  (2,4) = " + res[1] + "  expected 2");
//        System.out.println("  (1,1) = " + res[2] + "  expected 0");
//        System.out.println("  (1,3) = " + res[3] + "  expected 2");
//    }

    public static void main(String[] args) {
        AssignEdgeWeightsII sol = new AssignEdgeWeightsII();

        // Test 1: Star graph — root 1, leaves 2,3,4,5
        //   (2,3): path 2-1-3, dist=2 → 2^1=2
        //   (2,5): path 2-1-5, dist=2 → 2^1=2
        //   (1,4): dist=1 → 2^0=1
        int[][] star = {{1,2},{1,3},{1,4},{1,5}};
        int[][] q1 = {{2,3},{2,5},{1,4},{1,1}};
        int[] r1 = sol.assignEdgeWeights(star, q1);
        System.out.println("Test 1 - Star graph:");
        System.out.println("  (2,3) = " + r1[0] + "  expected 2");
        System.out.println("  (2,5) = " + r1[1] + "  expected 2");
        System.out.println("  (1,4) = " + r1[2] + "  expected 1");
        System.out.println("  (1,1) = " + r1[3] + "  expected 0");

        // Test 2: Balanced binary tree
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        //   (4,5): path 4-2-5, dist=2 → 2
        //   (4,3): path 4-2-1-3, dist=3 → 4
        //   (3,5): path 3-1-2-5, dist=3 → 4
        int[][] bbt = {{1,2},{1,3},{2,4},{2,5}};
        int[][] q2 = {{4,5},{4,3},{3,5},{1,4}};
        int[] r2 = sol.assignEdgeWeights(bbt, q2);
        System.out.println("\nTest 2 - Binary tree:");
        System.out.println("  (4,5) = " + r2[0] + "  expected 2");
        System.out.println("  (4,3) = " + r2[1] + "  expected 4");
        System.out.println("  (3,5) = " + r2[2] + "  expected 4");
        System.out.println("  (1,4) = " + r2[3] + "  expected 2");

        // Test 3: Single edge tree
        //   (1,2): dist=1 → 1
        int[][] single = {{1,2}};
        int[][] q3 = {{1,2},{2,1},{1,1}};
        int[] r3 = sol.assignEdgeWeights(single, q3);
        System.out.println("\nTest 3 - Single edge:");
        System.out.println("  (1,2) = " + r3[0] + "  expected 1");
        System.out.println("  (2,1) = " + r3[1] + "  expected 1");
        System.out.println("  (1,1) = " + r3[2] + "  expected 0");

        // Performance test: large chain n=1000
        int N = 1000;
        int[][] chain = new int[N - 1][2];
        for (int i = 0; i < N - 1; i++) chain[i] = new int[]{i + 1, i + 2};
        int[][] perfQ = {{1, N}, {1, N / 2}, {N / 2, N}};
        long t0 = System.nanoTime();
        int[] r4 = sol.assignEdgeWeights(chain, perfQ);
        long t1 = System.nanoTime();
        System.out.println("\nTest 4 - Chain n=1000:");
        System.out.printf("  (1,%d)     = %d%n", N, r4[0]);
        System.out.printf("  (1,%d)   = %d%n", N / 2, r4[1]);
        System.out.printf("  (%d,%d) = %d%n", N / 2, N, r4[2]);
        System.out.printf("  Elapsed: %d ns%n", (t1 - t0));  // ✅ fixed: %d for long
    }
}

