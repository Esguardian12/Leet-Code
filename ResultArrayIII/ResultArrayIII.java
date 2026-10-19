package ResultArrayIII;

package leetCode;

/* 3525. Find X Value of Array II
You are given an array of positive integers nums and a positive integer k. You are also given a 2D array queries, where queries[i] = [indexi, valuei, starti, xi].

You are allowed to perform an operation once on nums, where you can remove any suffix from nums such that nums remains non-empty.

The x-value of nums for a given x is defined as the number of ways to perform this operation so that the product of the remaining elements leaves a remainder of x modulo k.

For each query in queries you need to determine the x-value of nums for xi after performing the following actions:

Update nums[indexi] to valuei. Only this step persists for the rest of the queries.
Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to represent the empty prefix).
Return an array result of size queries.length where result[i] is the answer for the ith query.

A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.

A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.

Note that the prefix and suffix to be chosen for the operation can be empty.

Note that x-value has a different definition in this version.

 

Example 1:

Input: nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]

Output: [2,2,2]

Explanation:

For query 0, nums becomes [1, 2, 2, 4, 5], and the empty prefix must be removed. The possible operations are:
Remove the suffix [2, 4, 5]. nums becomes [1, 2].
Remove the empty suffix. nums becomes [1, 2, 2, 4, 5] with a product 80, which gives remainder 2 when divided by 3.
For query 1, nums becomes [1, 2, 2, 3, 5], and the prefix [1, 2, 2] must be removed. The possible operations are:
Remove the empty suffix. nums becomes [3, 5].
Remove the suffix [5]. nums becomes [3].
For query 2, nums becomes [1, 2, 2, 3, 5], and the empty prefix must be removed. The possible operations are:
Remove the suffix [2, 2, 3, 5]. nums becomes [1].
Remove the suffix [3, 5]. nums becomes [1, 2, 2].
Example 2:

Input: nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]

Output: [1,0]

Explanation:

For query 0, nums becomes [2, 2, 4, 8, 16, 32]. The only possible operation is:
Remove the suffix [2, 4, 8, 16, 32].
For query 1, nums becomes [2, 2, 4, 8, 16, 32]. There is no possible way to perform the operation.
Example 3:

Input: nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]

Output: [5]

 

Constraints:

1 <= nums[i] <= 109
1 <= nums.length <= 105
1 <= k <= 5
1 <= queries.length <= 2 * 104
queries[i] == [indexi, valuei, starti, xi]
0 <= indexi <= nums.length - 1
1 <= valuei <= 109
0 <= starti <= nums.length - 1
0 <= xi <= k - 1
 * 
 * Time Complexity: O((N + Q log N) * k), Where $N$ is the length of nums, $Q$ is the number of queries, and $k$ is the modulus bound ($1 ≤ k ≤ 5$). 
 * 			Building the segment tree takes ${O}(N * k)$ time. Each of the $Q$ queries involves a point update and a range query on the segment 
 * 			tree, each taking ${O}(\log N * k)$ time. Given $k ≤ 5$, this runs efficiently well within time limits.
 * Space Complexity: O(N* K), The segment tree arrays (treeProd of size $4N$ and treeCount of size $4N \times k$) dominate the auxiliary memory. Since 
 * 			$k ≤ 5$, this scales linearly as ${O}(N)$ space.
 */

import java.util.Arrays;

public class ResultArrayIII {

	private int[] treeProd;
	private int[][] treeCount;
	
	public int[] resultArray(int[] nums, int k, int[][] queries) {
		int n = nums.length;
		treeProd = new int[4 * n];
		treeCount = new int[4 * n][k];
		
		// Build initial segment tree
		build(1, 0, n - 1, nums, k);
		
		int[] result = new int[queries.length];
		
		for (int i = 0; i < queries.length; i++) {
			int idx = queries[i][0];
			int val = queries[i][1];
			int start = queries[i][2];
			int x = queries[i][3];
			
			// 1. Point Update
			update(1, 0, n - 1, idx, val, k);
			
			// 2. Range Query from start to end of array
			int[] queryRes = query(1, 0, n - 1, start, n - 1, k);
			
			// The counts are stored starting at index 1 in the result array of our query helper
			result[i] = queryRes[x + 1];
		}
		
		return result;
	}
	
	private void build(int node, int L, int R, int[] nums, int k) {
		if (L == R) {
			treeProd[node] = nums[L] % k;
			treeCount[node][treeProd[node]] = 1;
			return;
		}
		int mid = L + (R - L) / 2;
		build(2 * node, L, mid, nums, k);
		build(2 * node + 1, mid + 1, R, nums, k);
		merge(node, 2 * node, 2 * node + 1, k);
	}
	
	public void update(int node, int L, int R, int idx, int val, int k) {
		if (L == R) {
			treeProd[node] = val % k;
			Arrays.fill(treeCount[node], 0);
			treeCount[node][treeProd[node]] = 1;
			return;
		}
		int mid = L + (R - L) / 2;
		if (idx <= mid) {
			update(2 * node, L, mid, idx, val, k);
		} else {
			update(2 * node + 1, mid + 1, R, idx, val, k);
		}
		merge(node, 2 * node, 2 * node + 1, k);
	}
	
	private void merge(int node, int left, int right, int k) {
		treeProd[node] = (treeProd[left] * treeProd[right]) % k;
		Arrays.fill(treeCount[node], 0);
		
		for (int i = 0; i < k; i++) {
			// Include prefixes entirely in the left child
			treeCount[node][i] += treeCount[left][i];
			
			// Extend the entire left child with prefixes from the right child
			if (treeCount[right][i] > 0) {
				int combinedMod = (treeProd[left] * i) % k;
				treeCount[node][combinedMod] += treeCount[right][i];
			}
		}
	}
	
	// Returns an array where arr[0] is the totalProd and arr[1..k] are the prefix counts
	private int[] query(int node, int L, int R, int qL, int qR, int k) {
		if (qL <= L && R <= qR) {
			int[] res = new int[k + 1];
			res[0] = treeProd[node];
			for (int i = 0; i < k; i++) {
				res[i + 1] = treeCount[node][i];
			}
			return res;
		}
		
		int mid = L + (R - L) / 2;
		if (qR <= mid) {
			return query(2 * node, L, mid, qL, qR, k);
		} else if (qL > mid) {
			return query(2 * node + 1, mid + 1, R, qL, qR, k);
		} else {
			int[] leftRes = query(2 * node, L, mid, qL, qR, k);
			int[] rightRes = query(2 * node + 1, mid + 1, R, qL, qR, k);
			
			int[] res = new int[k + 1];
			res[0] = (leftRes[0] * rightRes[0]) % k;
			
			for (int i = 0; i < k; i++) {
				res[i + 1] += leftRes[i + 1];
				if (rightRes[i + 1] > 0) {
					int combinedMod = (leftRes[0] * i) % k;
					res[combinedMod + 1] += rightRes[i + 1];
				}
			}
			return res;
		}
	}

	public static void main(String[] args) {
		ResultArrayIII sol = new ResultArrayIII();

		// Example 1
		int[] nums1 = {1, 2, 3, 4, 5};
		int k1 = 3;
		int[][] queries1 = {
			{2, 2, 0, 2},
			{3, 3, 3, 0},
			{0, 1, 0, 1}
		};
		System.out.println("Output 1: " + Arrays.toString(sol.resultArray(nums1, k1, queries1)));
		// Expected: [2, 2, 2]
		System.out.println("-----------------------------------");

		// Example 2
		int[] nums2 = {1, 2, 4, 8, 16, 32};
		int k2 = 4;
		int[][] queries2 = {
			{0, 2, 0, 2},
			{0, 2, 0, 1}
		};
		System.out.println("Output 2: " + Arrays.toString(sol.resultArray(nums2, k2, queries2)));
		// Expected: [1, 0]
		System.out.println("-----------------------------------");

		// Example 3
		int[] nums3 = {1, 1, 2, 1, 1};
		int k3 = 2;
		int[][] queries3 = {
			{2, 1, 0, 1}
		};
		System.out.println("Output 3: " + Arrays.toString(sol.resultArray(nums3, k3, queries3)));
		// Expected: [5]
	}
}
