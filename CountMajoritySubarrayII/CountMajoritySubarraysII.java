package CountMajoritySubarrayII;

/* 3739. Count Subarrays With Majority Element II
 * You are given an integer array nums and an integer target.

Return the number of subarrays of nums in which target is the majority element.

The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.

 

Example 1:

Input: nums = [1,2,2,3], target = 2

Output: 5

Explanation:

Valid subarrays with target = 2 as the majority element:

nums[1..1] = [2]
nums[2..2] = [2]
nums[1..2] = [2,2]
nums[0..2] = [1,2,2]
nums[1..3] = [2,2,3]
So there are 5 such subarrays.

Example 2:

Input: nums = [1,1,1,1], target = 1

Output: 10

Explanation:

​​​​​​​All 10 subarrays have 1 as the majority element.

Example 3:

Input: nums = [1,2,3], target = 4

Output: 0

Explanation:

target = 4 does not appear in nums at all. Therefore, there cannot be any subarray where 4 is the majority element. Hence the answer is 0.

 

Constraints:

1 <= nums.length <= 10​​​​​​​5
1 <= nums[i] <= 10​​​​​​​9
1 <= target <= 109
 *
 * Time Complexity: O(n), Creating the array takes O(n) time. The loop runs n times. Each iteration performs only constant-time operations: comparisons,
 * 			array accesses, increment/decrements, additions/subtractions
 * Space Complexity: O(n), The extra spaces used is: pre array of size 2n + 1 -> O(n). A few integer/long variables -> O(1)
 */


public class CountMajoritySubarraysII {
	
	public long countMajoritySubarraysII(int[] nums, int target) {
		int n = nums.length;
		int[] pre = new int[n * 2 + 1];
		pre[n] = 1;
		int cnt = n;
		long ans = 0, presum = 0;
		for(int i = 0; i < n; i++) {
			if(nums[i] == target) {
				presum += pre[cnt];
				cnt++;
				pre[cnt]++;
			} else {
				cnt--;
				presum -= pre[cnt];
				pre[cnt]++;
			}
			ans += presum;
		}
		return ans;
	}
	
	public static void main(String[] args) {
        CountMajoritySubarraysII obj = new CountMajoritySubarraysII();

        int[] nums = {1, 2, 2, 1, 2};
        int target = 2;

        long result = obj.countMajoritySubarraysII(nums, target);

        System.out.println("Number of Majority Subarrays: " + result);
    }
}

