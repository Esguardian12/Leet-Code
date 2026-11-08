package CountServers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 2747. Count Zero Request Servers
You are given an integer n denoting the total number of servers and a 2D 0-indexed integer array logs, where logs[i] = [server_id, time] denotes that the server with id server_id received a request at time time.

You are also given an integer x and a 0-indexed integer array queries.

Return a 0-indexed integer array arr of length queries.length where arr[i] represents the number of servers that did not receive any requests during the time interval [queries[i] - x, queries[i]].

Note that the time intervals are inclusive.

 

Example 1:

Input: n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
Output: [1,2]
Explanation: 
For queries[0]: The servers with ids 1 and 2 get requests in the duration of [5, 10]. Hence, only server 3 gets zero requests.
For queries[1]: Only the server with id 2 gets a request in duration of [6,11]. Hence, the servers with ids 1 and 3 are the only servers that do not receive any requests during that time period.

Example 2:

Input: n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
Output: [0,1]
Explanation: 
For queries[0]: All servers get at least one request in the duration of [1, 3].
For queries[1]: Only server with id 3 gets no request in the duration [2,4].

 

Constraints:

1 <= n <= 105
1 <= logs.length <= 105
1 <= queries.length <= 105
logs[i].length == 2
1 <= logs[i][0] <= n
1 <= logs[i][1] <= 106
1 <= x <= 105
x < queries[i] <= 106
 * 
 * Time Complexity: O(m log m + L log L), • Sorting: Sorting the indexedQueries tracking array takes \(\mathcal{O}(m \log m)\). Sorting the logs matrix takes 
 * 	\(\mathcal{O}(L \log L)\).
• Sliding Window: While there is a nested loop template, the pointers left and right only increment forward across the entire algorithm execution. 
	They never reset. Consequently, each log configuration is processed in the window at most once, which takes a collective time of \(\mathcal{O}(L)\).
• Overall: The runtime is strictly dominated by the structural sorting constraints, which simplifies to \(\mathcal{O}(m \log m + L \log L)\).
 * Space Complexity: O(m + L + n), • Tracking Structures: The tracking grid indexedQueries takes \(\mathcal{O}(m)\) space.
• Frequency Mapping: The serverCounts map stores at most \(n\) distinct server keys (or bounded by \(L\) entries if total logs are fewer than total servers), 
	taking up \(\mathcal{O}(\min(n, L))\) space.
• Result Array: The final answer allocation ans uses \(\mathcal{O}(m)\) auxiliary structure spacing.
 */

public class CountServers {
	
	public int[] countServers(int n, int[][] logs, int x, int[] queries) {
		int m = queries.length;
		int[][] indexedQueries = new int[m][2];
		for (int i = 0; i < m; i++) {
			indexedQueries[i][0] = queries[i];
			indexedQueries[i][1] = i;
		}
		
		// Sort queries by their time values
		Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));
		// Sort logs by their timestamp values
		Arrays.sort(logs, (a, b) -> Integer.compare(a[1],  b[1]));
		
		int[] ans = new int[m];
		Map<Integer, Integer> serverCounts = new HashMap<>();
		int left = 0, right = 0;
		int numLogs = logs.length;
		
		for (int i = 0; i < m; i++) {
			int qTime = indexedQueries[i][0];
			int qIdx = indexedQueries[i][1];
			int windowStart = qTime - x;
			int windowEnd = qTime;
			
			// Add all logs whose timestamps are <= windowEnd
			while (right < numLogs && logs[right][1] <= windowEnd) {
				int serverId = logs[right][0];
				serverCounts.put(serverId, serverCounts.getOrDefault(serverId, 0) + 1);
				right++;
			}
			
			// Remove logs whose timestamps are < windowStart
			while (left < numLogs && logs[left][1] < windowStart) {
				int serverId = logs[left][0];
				int count = serverCounts.get(serverId);
				if ( count == 1) {
					serverCounts.remove(serverId);
				} else {
					serverCounts.put(serverId, count - 1);
				}
				left++;
			}
			
			// Zero-request servers = total servers (n) - active servers in the window
			ans[qIdx] = n - serverCounts.size();
		}
		
		return ans;
	}
	
	 public static void main(String[] args) {
	        CountServers cs = new CountServers();

	        // Example 1
	        int n1 = 3;
	        int[][] logs1 = {{1, 3}, {2, 6}, {1, 5}};
	        int x1 = 5;
	        int[] queries1 = {10, 11};
	        int[] result1 = cs.countServers(n1, logs1, x1, queries1);
	        System.out.println("Example 1 Output: " + Arrays.toString(result1)); // Expected: [1, 2]

	        // Example 2
	        int n2 = 3;
	        int[][] logs2 = {{2, 4}, {2, 1}, {1, 2}, {3, 1}};
	        int x2 = 2;
	        int[] queries2 = {3, 4};
	        int[] result2 = cs.countServers(n2, logs2, x2, queries2);
	        System.out.println("Example 2 Output: " + Arrays.toString(result2)); // Expected: [0, 1]
	    }

}
