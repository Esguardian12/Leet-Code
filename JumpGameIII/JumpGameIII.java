package JumpGameIII;

package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/* 1306. Jump Game III
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
 * 
 * Notice that you can not jump outside of the array at any time.
   Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 * 
 * Time Complexity: O(n), Each index is visited at most once. For every index, we process at most 2 jumps.
 * Space Complexity: O(n), visited[] array -> O(n), Queue in worst case can hold O(n) elements.
 */

public class JumpGameIII {
	
	public boolean canReach(int[] arr, int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		boolean[] visited = new boolean[arr.length];
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			if (arr[curr] == 0)
				return true;
			
			int[] nextJumps = {curr + arr[curr], curr - arr[curr]};
			for(int nextIdx : nextJumps) {
				if(nextIdx >= 0 && nextIdx < arr.length && !visited[nextIdx]) {
					visited[nextIdx] = true;
					q.add(nextIdx);
				}
			}
	  }
		return false;
	}
	
	public static void main(String[] args) {
		JumpGameIII sol = new JumpGameIII();
		
		int[] arr1 = {4, 2 ,3 ,0 ,3 ,1 ,2};
		int start1 = 5;
		
		boolean result1 = sol.canReach(arr1, start1);
		System.out.println("Can reach zero from start index " + start1 + ": " + result1);
		
		int[] arr2 = {3, 0, 2, 1, 2};
		int start2 = 2;
		
		boolean result2 = sol.canReach(arr2,  start2);
		
		System.out.println("Can reach zero from start index " + start2 + ": " + result2);
	}
	
}


