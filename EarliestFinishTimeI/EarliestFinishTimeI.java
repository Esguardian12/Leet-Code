package EarliestFinishTimeI;

/* 3633. Earliest Finish Time for Land and Water Rides I
 * You are given two categories of theme park attractions: land rides and water rides.

Land rides
landStartTime[i] – the earliest time the ith land ride can be boarded.
landDuration[i] – how long the ith land ride lasts.
Water rides
waterStartTime[j] – the earliest time the jth water ride can be boarded.
waterDuration[j] – how long the jth water ride lasts.
A tourist must experience exactly one ride from each category, in either order.

A ride may be started at its opening time or any later moment.
If a ride is started at time t, it finishes at time t + duration.
Immediately after finishing one ride the tourist may board the other (if it is already open) or wait until it opens.
Return the earliest possible time at which the tourist can finish both rides.

 

Example 1:

Input: landStartTime = [2,8], landDuration = [4,1], waterStartTime = [6], waterDuration = [3]

Output: 9

Explanation:​​​​​​​

Plan A (land ride 0 → water ride 0):
Start land ride 0 at time landStartTime[0] = 2. Finish at 2 + landDuration[0] = 6.
Water ride 0 opens at time waterStartTime[0] = 6. Start immediately at 6, finish at 6 + waterDuration[0] = 9.
Plan B (water ride 0 → land ride 1):
Start water ride 0 at time waterStartTime[0] = 6. Finish at 6 + waterDuration[0] = 9.
Land ride 1 opens at landStartTime[1] = 8. Start at time 9, finish at 9 + landDuration[1] = 10.
Plan C (land ride 1 → water ride 0):
Start land ride 1 at time landStartTime[1] = 8. Finish at 8 + landDuration[1] = 9.
Water ride 0 opened at waterStartTime[0] = 6. Start at time 9, finish at 9 + waterDuration[0] = 12.
Plan D (water ride 0 → land ride 0):
Start water ride 0 at time waterStartTime[0] = 6. Finish at 6 + waterDuration[0] = 9.
Land ride 0 opened at landStartTime[0] = 2. Start at time 9, finish at 9 + landDuration[0] = 13.
Plan A gives the earliest finish time of 9.

Example 2:

Input: landStartTime = [5], landDuration = [3], waterStartTime = [1], waterDuration = [10]

Output: 14

Explanation:​​​​​​​

Plan A (water ride 0 → land ride 0):
Start water ride 0 at time waterStartTime[0] = 1. Finish at 1 + waterDuration[0] = 11.
Land ride 0 opened at landStartTime[0] = 5. Start immediately at 11 and finish at 11 + landDuration[0] = 14.
Plan B (land ride 0 → water ride 0):
Start land ride 0 at time landStartTime[0] = 5. Finish at 5 + landDuration[0] = 8.
Water ride 0 opened at waterStartTime[0] = 1. Start immediately at 8 and finish at 8 + waterDuration[0] = 18.
Plan A provides the earliest finish time of 14.​​​​​​​

 
Constraints:

1 <= n, m <= 100
landStartTime.length == landDuration.length == n
waterStartTime.length == waterDuration.length == m
1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 1000
 * 
 * Time Complexity: O(n*m), nested loop over both task sets
 * Space Complexity: O(1)
 */

public class EarliestFinishTimeI {

	public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
		int landWater = solve(landStartTime, landDuration, waterStartTime, waterDuration);
		int waterLand = solve(waterStartTime, waterDuration, landStartTime, landDuration);
		return Math.min(landWater, waterLand);
	}
	
//	private int solve(int[] start1, int[] duration1, int[] start2, int[] duration2) {
//		int finish1 = Integer.MAX_VALUE;
//		for (int i = 0; i < start1.length; i++) {
//			finish1 = Math.min(finish1,  Math.max(start1[i], finish1) + duration1[i]);
//		}
//		int finish2 = Integer.MAX_VALUE;
//		for (int i = 0; i < start2.length; i++) {
//			finish2 = Math.min(finish2, Math.max(start2[i], finish2) + duration2[i]);
//		}
//		return finish2;
//	}
	
	/* If n and m are large, this can be optimized to O(n log n) by sorting set1 by finish time
	 * and using binary search to find the best pairing for each set2 task — but for typical
	 * input sizes the brute force is fine.
	 */
	
	private int solve(int[] start1, int[] duration1, int[] start2, int[] duration2) {
	    int minFinish = Integer.MAX_VALUE;

	    for (int i = 0; i < start1.length; i++) {
	        int finish1 = start1[i] + duration1[i]; // finish task i from set1

	        for (int j = 0; j < start2.length; j++) {
	            // do set2 task j immediately after
	            int finish2 = Math.max(start2[j], finish1) + duration2[j];
	            minFinish = Math.min(minFinish, finish2);
	        }
	    }
	    return minFinish;
	}
	
	public static void main(String[] args) {
	    EarliestFinishTimeI sol = new EarliestFinishTimeI();

	    // Test 1: Basic case
	    int[] landStart    = {1, 3};
	    int[] landDur      = {2, 1};
	    int[] waterStart   = {2, 5};
	    int[] waterDur     = {1, 2};
	    System.out.println("Test 1: " + sol.earliestFinishTime(landStart, landDur, waterStart, waterDur));
	    // Expected: min of (land→water path, water→land path)

	    // Test 2: Single task each
	    int[] landStart2   = {0};
	    int[] landDur2     = {3};
	    int[] waterStart2  = {2};
	    int[] waterDur2    = {1};
	    System.out.println("Test 2: " + sol.earliestFinishTime(landStart2, landDur2, waterStart2, waterDur2));

	    // Test 3: Water tasks start much later
	    int[] landStart3   = {0, 1, 2};
	    int[] landDur3     = {1, 1, 1};
	    int[] waterStart3  = {10, 11, 12};
	    int[] waterDur3    = {1, 1,  1};
	    System.out.println("Test 3: " + sol.earliestFinishTime(landStart3, landDur3, waterStart3, waterDur3));
	    
	    //The pattern in Test 3 is worth noting — when one group starts much later, it's always better to
	    //do the early group first, then slot into the late group's window.
	}
}
