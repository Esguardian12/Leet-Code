package BlockPlaceQueries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/* 3161. Block Placement Queries
 * There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.
 * You are given a 2D array queries, which contains two types of queries:

For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed that there is no obstacle at distance x when the query is asked.
For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in the range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are separate.
Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2, and false otherwise.

Example 1:
Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
Output: [false,true,true]
Explanation:
For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.

Example 2:
Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
Output: [true,true,false]

Explanation:

Place an obstacle at x = 7 for query 0. A block of size at most 7 can be placed before x = 7.
Place an obstacle at x = 2 for query 2. Now, a block of size at most 5 can be placed before x = 7, and a block of size at most 2 before x = 2.
 

Constraints:

1 <= queries.length <= 15 * 104
2 <= queries[i].length <= 3
1 <= queries[i][0] <= 2
1 <= x, sz <= min(5 * 104, 3 * queries.length)
The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
The input is generated such that there is at least one query of type 2.
 * 
 * Time Complexity: O(n log M), where n = number of queries, M = coordinate range (500001 here). Since M is a constant (500001), this is effectively 
 *         O(n log n) in practice.
 * Space Complexity: O(M + n), dominated by the BIT array size, which is fixed at 500k regardless of input.
 * 
 * How It Works (Quick Summary)
 * The algorithm runs backwards through the queries. It starts with all blocks already placed,
 * then "un-places" them one by one. The BIT stores the maximum gap seen up to each index, so
 * type-2 queries become a simple prefix-max lookup in O(log M).
 */

public class BlockPlaceQueries {
	
	int[] bt;
	
	void update(int idx, int val) {
		for (idx++; idx < bt.length; idx += idx & -idx) {
			bt[idx] = Math.max(bt[idx], val);
		}
	}
	
	int query(int idx) {
		int res = 0;
		for (idx++; idx > 0; idx -= idx & -idx) {
			res = Math.max(res,  bt[idx]);
		}
		return res;
	}
	
	public List<Boolean> getResults(int[][] queries){
		int mx = 500001;
		
		TreeSet<Integer> st = new TreeSet<>();
		st.add(0);
		st.add(mx);
		
		for(int[] q : queries) {
			if (q[0] == 1) st.add(q[1]);
		}
		
		bt = new int [mx + 2];
		
		int pre = 0;
		for(int x : st) {
			if (x > 0) update(x, x - pre);
			pre = x;
		}
		
		List<Boolean> ans = new ArrayList<>();
		for(int i = queries.length - 1; i >= 0; i--) {
			int[] q = queries[i];
			
			if(q[0] == 1) {
				int x = q[1];
				st.remove(x);
				
				int lower = st.lower(x);
				int higher = st.higher(lower);
				
				update(higher, higher - lower);
				
			} else {
				int x = q[1];
				int sz = q[2];
				
				int lower = st.floor(x);
				
				ans.add(Math.max(x - lower,  query(lower)) >= sz);
			}
		}
		
		Collections.reverse(ans);
	    return ans;
	}
	
	/* Key Takeaways
	 * Reverse Thinking: If adding things splits your data, processing in reverse merges them.
	 * Merging intervals only increases length, making a simple Fenwick Tree perfect for tracking the maximums.
	 */
	
	public static void main(String[] args) {
	    BlockPlaceQueries solver = new BlockPlaceQueries();

	    // Example: place blocks and check if a block of size 'sz' fits at position x
	    // Query type 1: [1, x]       → place a block at position x
	    // Query type 2: [2, x, sz]   → can a block of size sz fit in [0..x]?
	    int[][] queries = {
	        {1, 3},       // place block at 3   → gaps: [0,3]=3, [3,500001]
	        {2, 3, 3},    // can size-3 fit in [0..3]?  → true (gap [0,3] = 3)
	        {2, 3, 4},    // can size-4 fit in [0..3]?  → false
	        {1, 7},       // place block at 7
	        {2, 7, 4},    // can size-4 fit in [0..7]?  → true (gap [3,7] = 4)
	        {2, 5, 5},    // can size-5 fit in [0..5]?  → false (max gap left of 5 is 3)
	    };

	    List<Boolean> results = solver.getResults(queries);

	    System.out.println("Query Results:");
	    int typeTwoIdx = 0;
	    for (int[] q : queries) {
	        if (q[0] == 2) {
	            System.out.printf("  Can fit size %d at x=%d? → %s%n",
	                q[2], q[1], results.get(typeTwoIdx++));
	        }
	    }
	}
}
