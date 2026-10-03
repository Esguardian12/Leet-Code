package FrequencySort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 1636. Sort Array by Increasing Frequency
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. 
 * If multiple values have the same frequency, sort them in decreasing order.
 * Return the sorted array.

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
 

Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100
 *
 * Time Complexity: O(n+k log k) -> worst case O(n log n), k = number of unique elements. In the worst case every element is unique so k = n, making
 * 			the sort O(n log n) and the overall time O(n log n).
 * Space Complexity: O(n), All three together are bounded by O(n) since k ≤ n.
 */

public class FrequencySort {
	
	public int[] frequencySort(int[] nums) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			}
			else {
				map.put(nums[i], 1);
			}
		}
		List<Integer> list = new ArrayList<Integer>(map.keySet());
		Collections.sort(list, (a,b) -> {           
			if (map.get(a).equals(map.get(b))) {   // used to be if (map.get(a) == map.get(b)) {  One Bug To Watch Out For    
				return b - a;                      // In the comparator, map.get(a) == map.get(b) compares Integer objects with == (reference
			}                                      // equality) instead of .equals(). This can silently fail for values outside Java's Integer cache
			else {                                 // range (-128 to 127). The safe fix is:  if (map.get(a).equals(map.get(b))) {
				return map.get(a) - map.get(b);
			}
		});
		int result[] = new int[nums.length];
		int index = 0;
		for (int num : list) {
			for(int i = 0; i < map.get(num); i++) {
				result[index++] = num;
			}
		}
		return result;
	}

	  public static void main(String[] args) {
	        FrequencySort sol = new FrequencySort();

	        // Test 1: [1, 1, 2, 2, 2, 3]
	        // Frequencies: {1->2, 2->3, 3->1}
	        // Sort by freq asc, ties break by value desc
	        // Order: 3(freq1), 1(freq2), 2(freq3)
	        // Expected: [3, 1, 1, 2, 2, 2]
	        int[] t1 = sol.frequencySort(new int[]{1, 1, 2, 2, 2, 3});
	        System.out.println("Test 1: " + Arrays.toString(t1));
	        System.out.println("Expected: [3, 1, 1, 2, 2, 2]");

	        // Test 2: [2, 3, 1, 3, 2]
	        // Frequencies: {1->1, 2->2, 3->2}
	        // Ties (2 and 3 both freq 2): higher value first -> 3 before 2
	        // Order: 1(freq1), 3(freq2), 2(freq2)
	        // Expected: [1, 3, 3, 2, 2]
	        int[] t2 = sol.frequencySort(new int[]{2, 3, 1, 3, 2});
	        System.out.println("\nTest 2: " + Arrays.toString(t2));
	        System.out.println("Expected: [1, 3, 3, 2, 2]");

	        // Test 3: [-1, 1, -6, 4, 5, -6, 1, 4, 1]
	        // Frequencies: {-1->1, 1->3, -6->2, 4->2, 5->1}
	        // Sort by freq asc, ties break by value desc:
	        // freq1: 5, -1  (5 > -1 so 5 first)
	        // freq2: 4, -6  (4 > -6 so 4 first)
	        // freq3: 1
	        // Expected: [5, -1, 4, 4, -6, -6, 1, 1, 1]
	        int[] t3 = sol.frequencySort(new int[]{-1, 1, -6, 4, 5, -6, 1, 4, 1});
	        System.out.println("\nTest 3: " + Arrays.toString(t3));
	        System.out.println("Expected: [5, -1, 4, 4, -6, -6, 1, 1, 1]");
	    }
}

