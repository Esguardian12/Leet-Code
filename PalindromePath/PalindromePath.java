package PalindromePath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 3841. Palindromic Path Queries in a Tree
You are given an undirected tree with n nodes labeled 0 to n - 1. This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an undirected edge between nodes ui and vi.

You are also given a string s of length n consisting of lowercase English letters, where s[i] represents the character assigned to node i.

You are also given a string array queries, where each queries[i] is either:

"update ui c": Change the character at node ui to c. Formally, update s[ui] = c.
"query ui vi": Determine whether the string formed by the characters on the unique path from ui to vi (inclusive) can be rearranged into a palindrome.
Return a boolean array answer, where answer[j] is true if the jth query of type "query ui vi"​​​​​​​ can be rearranged into a palindrome, and false otherwise.

 

Example 1:

Input: n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1 b","query 0 2"]

Output: [true,false]

Explanation:

"query 0 2": Path 0 → 1 → 2 gives "aac", which can be rearranged to form "aca", a palindrome. Thus, answer[0] = true.
"update 1 b": Update node 1 to 'b', now s = "abc".
"query 0 2": Path characters are "abc", which cannot be rearranged to form a palindrome. Thus, answer[1] = false.
Thus, answer = [true, false].

Example 2:

Input: n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1 2","update 0 b","query 2 3","update 3 a","query 1 3"]

Output: [false,false,true]

Explanation:

"query 1 2": Path 1 → 0 → 2 gives "bac", which cannot be rearranged to form a palindrome. Thus, answer[0] = false.
"update 0 b": Update node 0 to 'b', now s = "bbca".
"query 2 3": Path 2 → 0 → 3 gives "cba", which cannot be rearranged to form a palindrome. Thus, answer[1] = false.
"update 3 a": Update node 3 to 'a', s = "bbca".
"query 1 3": Path 1 → 0 → 3 gives "bba", which can be rearranged to form "bab", a palindrome. Thus, answer[2] = true.
Thus, answer = [false, false, true].

 

Constraints:

1 <= n == s.length <= 5 * 104
edges.length == n - 1
edges[i] = [ui, vi]
0 <= ui, vi <= n - 1
s consists of lowercase English letters.
The input is generated such that edges represents a valid tree.
1 <= queries.length <= 5 * 104​​​​​​​
queries[i] = "update ui c" or
queries[i] = "query ui vi"
0 <= ui, vi <= n - 1
c is a lowercase English letter.
 * 
 * Time Complexity: (\{O}(n \log n + q \log^2 n)\)

where \(n\) is the number of nodes in the tree and \(q\) is the number of queries.
• Tree Setup & Decomposition: The initial tree parsing, adjacency tree construction, dfs1, and dfs2 take linear time, \(\{O}(n)\).
• Segment Tree Initialization: The initial loop populates the segment tree by making \(n\) point updates. Each update takes \(\{O}(\log n)\), 
	costing \(\{O}(n \log n)\).
• Handling Updates: String splitting and parsing run in \(\{O}(1)\). Point updates on the segment tree take \(\{O}(\log n)\).
• Handling Queries: Moving up the tree via HLD jumps across at most \(\{O}(\log n)\) heavy chains. At each jump, a range query is executed on the 
	segment tree, which takes \(\{O}(\log n)\). Thus, every path query evaluates in \(\{O}(\log^2 n)\).
 * Space Complexity: \(\{O}(n)\)
• Graph Storage: The adjacency list g requires \(\{O}(n)\) space to track edges.
• Decomposition Structures: The tracking tracking arrays (parent, depth, heavy, head, pos, chars) each scale linearly to require \(\{O}(n)\) space.
• Segment Tree Data: The layout array tree is sized at \(4n\), which scales directly as \(\{O}(n)\).
• No additional dynamic arrays are generated mid-query execution, keeping total auxiliary memory consumption securely restricted to linear allocations.
 */

public class PalindromePath {
		private List<Integer>[] g;
		private int[] parent, depth, heavy, head, pos;
		private int[] tree;
		private char[] chars;
		private int curPos;
		
		public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
			g = new ArrayList[n];
			for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
			for (int[] e : edges) {
				g[e[0]].add(e[1]);
				g[e[1]].add(e[0]);
			}
			
			chars = s.toCharArray();
			parent = new int[n];
			depth = new int[n];
			heavy = new int[n];
			Arrays.fill(heavy, -1);
			head = new int[n];  // Move here: completely initialized before DFS paths execute
			pos = new int[n];
			tree = new int[4 * n];
			
			dfs1(0, -1, 0);
			curPos = 0;
			dfs2(0, 0);
			
			for (int i = 0; i < n; i++) {
				update(1, 0, n - 1, pos[i], 1 << (chars[i] - 'a'));
			}
			
			List<Boolean> answer = new ArrayList<>();
			for (String q : queries) {
				String[] parts = q.split(" ");
				if (parts[0].equals("update")) { // Fixed parts -> parts[0]
					int u = Integer.parseInt(parts[1]); // Fixed parts -> parts[1]
					char newChar = parts[2].charAt(0);  // Fixed parts -> parts[2]
					int oldMask = 1 << (chars[u] - 'a');
					int newMask = 1 << (newChar - 'a');
					chars[u] = newChar;
					update(1, 0, n - 1, pos[u], oldMask ^ newMask);
				} else {
					int u = Integer.parseInt(parts[1]); // Fixed parts -> parts[1]
					int v = Integer.parseInt(parts[2]); // Fixed parts -> parts[2]
					int mask = queryPath(u, v, n);
					// A path can form a palindrome if the XOR result has <= 1 bit set
					answer.add(mask == 0 || (mask & (mask - 1)) == 0);
				}
			}
			return answer;
		}
		
		private int dfs1(int u, int p, int d) {
			parent[u] = p;
			depth[u] = d;
			int size = 1;
			int maxSubtreeSize = 0;
			for (int v : g[u]) {
				if (v != p) {
					int subSize = dfs1(v, u, d + 1);
					size += subSize;
					if (subSize > maxSubtreeSize) {
						maxSubtreeSize = subSize;
						heavy[u] = v;
					}
				}
			}
			return size;
		}
		
		private void dfs2(int u, int h) {
			head[u] = h;
			pos[u] = curPos++;
			if (heavy[u] != -1) {
				dfs2(heavy[u], h);
			}
			for (int v : g[u]) {
				if (v != parent[u] && v != heavy[u]) {
					dfs2(v, v);
				}
			}
		}
		
		private void update (int node, int l, int r, int idx, int val) {
			if (l == r) {
				tree[node] ^= val;
				return;
			}
			int mid = (l + r) / 2;
			if (idx <= mid) update (node * 2, l, mid, idx, val);
			else update(node * 2 + 1, mid + 1, r, idx, val);
			tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
		}
		
		private int query(int node, int l, int r, int ql, int qr) {
			if (qr < l || ql > r) return 0;
			if (ql <= l && r <= qr) return tree[node];
			int mid = (l + r) / 2;
			return query(node * 2, l, mid, ql, qr) ^ query(node * 2 + 1, mid + 1, r, ql, qr);
		}
		
		private int queryPath(int u, int v, int n) {
			int res = 0;
			while (head[u] != head[v]) {
				if (depth[head[u]] < depth[head[v]]) {
					int temp = u; u = v; v = temp;
				}
				res ^= query(1, 0, n - 1, pos[head[u]], pos[u]);
				u = parent[head[u]];
			}
			if (depth[u] > depth[v]) {
				int temp = u; u = v; v = temp;
			}
			res ^= query(1, 0, n - 1, pos[u], pos[v]);
			return res;
		}

		// MAIN METHOD
		public static void main(String[] args) {
			PalindromePath solver = new PalindromePath();

			int n = 4;
			int[][] edges = {{0, 1}, {0, 2}, {0, 3}};
			String s = "abca";
			String[] queries = {
				"query 1 2",
				"update 0 b",
				"query 2 3",
				"update 3 a",
				"query 1 3"
			};

			List<Boolean> result = solver.palindromePath(n, edges, s, queries);
			System.out.println("Query Results: " + result); 
			// Out: [false, false, true]
		}
}

