package CheckSortedArray;

/* 1752. Check if Array Is Sorted and Rotated
 * Given an array nums, return true if the array was originally sorted in non-decreasing order,
 *  then rotated some number of positions (including zero). Otherwise, return false.
 * There may be duplicates in the original array.
 * Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.

Example 1:

Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].
Example 2:

Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.
Example 3:

Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
 * 
 * Time Complexity: T(n) = O(n), The loop traverses the array once: So, the time complexity is O(n).
 * Space Complexity: S(n) = O(1), The algorithm uses only a few extra variables(n, inversionCount, i). So, the space complexity is O(1).
 */

public class CheckSortedArray {
	
	public boolean check(int[] nums) {
		int n = nums.length;
		if(n <= 1)
			return true;
		
		int inversionCount = 0;
		
		for(int i = 1; i < n; ++i) {
			if(nums[i] < nums[i - 1]) {
				inversionCount++;
			}
		}
		
		if(nums[0] < nums[n - 1]) {
			inversionCount++;
		}
		
		return inversionCount <= 1;
	}
	
	public static void main(String[] args) {

        CheckSortedArray obj = new CheckSortedArray();

        int[] arr1 = {3, 4, 5, 1, 2};
        int[] arr2 = {2, 1, 3, 4};
        int[] arr3 = {1, 2, 3};
        int[] arr4 = {1, 1, 1};

        System.out.println(obj.check(arr1)); // true
        System.out.println(obj.check(arr2)); // false
        System.out.println(obj.check(arr3)); // true
        System.out.println(obj.check(arr4)); // true
    }


    
}
