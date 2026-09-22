package EarliestFinishTimeII;

/* 3635. Earliest Finish Time for Land and Water Rides II
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

1 <= n, m <= 5 * 104
landStartTime.length == landDuration.length == n
waterStartTime.length == waterDuration.length == m
1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 105
 * 
 * Time Complexity: O(n*m)
 * Space Complexity: O(1)
 */

public class EarliestFinishTimeII {
	
	public int earliestFinshTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
		//Precompute the single best(minimum) finish for each group
		int minLandFinish = Integer.MAX_VALUE;
		int minWaterFinish = Integer.MAX_VALUE;
		
		for(int i = 0; i < landStartTime.length; i++)
			minLandFinish = Math.min(minLandFinish, landStartTime[i] + landDuration[i]);
		
		for(int j = 0; j < waterStartTime.length; j++)
			minWaterFinish = Math.min(minWaterFinish, waterStartTime[j] + waterDuration[j]);
		
		int ans = Integer.MAX_VALUE;
		
		//Land -> Water: fix best land, iterate water
		for (int j = 0; j < waterStartTime.length; j++) {
			int finish = Math.max(minLandFinish, waterStartTime[j]) + waterDuration[j];
			ans = Math.min(ans, finish);
		}
		
		// Water -> Land: fix best Water, iterate Land
		for (int i = 0; i < landStartTime.length; i++) {
			int finish = Math.max(minWaterFinish, landStartTime[i]) + landDuration[i];
			ans = Math.min(ans, finish);
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
	    EarliestFinishTimeII sol = new EarliestFinishTimeII();

	    // Test 1: Original failing case from LeetCode
	    int[] land1  = {2, 8};
	    int[] lDur1  = {4, 1};
	    int[] water1 = {6};
	    int[] wDur1  = {3};
	    System.out.println("Test 1: " + sol.earliestFinshTime(land1, lDur1, water1, wDur1));
	    // Expected: 9  (land[0] finishes at 6 → water[0] starts at 6, finishes at 9)

	    // Test 2: Water first is better
	    int[] land2  = {0};
	    int[] lDur2  = {3};
	    int[] water2 = {2};
	    int[] wDur2  = {1};
	    System.out.println("Test 2: " + sol.earliestFinshTime(land2, lDur2, water2, wDur2));
	    // Expected: 4  (land[0] finishes at 3 → water[0] starts at 3, finishes at 4)

	    // Test 3: Multiple tasks each side
	    int[] land3  = {1, 3};
	    int[] lDur3  = {2, 1};
	    int[] water3 = {2, 5};
	    int[] wDur3  = {1, 2};
	    System.out.println("Test 3: " + sol.earliestFinshTime(land3, lDur3, water3, wDur3));
	    // Expected: 4  (land[0] finishes at 3 → water[0] starts at 3, finishes at 4)

	    // Test 4: Land tasks finish long before water window opens
	    int[] land4  = {0, 1, 2};
	    int[] lDur4  = {1, 1, 1};
	    int[] water4 = {10, 11, 12};
	    int[] wDur4  = {1,  1,  1};
	    System.out.println("Test 4: " + sol.earliestFinshTime(land4, lDur4, water4, wDur4));
	    // Expected: 11 (minLandFinish=1 → water[0] starts at max(1,10)=10, finishes at 11)

	    // Test 5: Water finishes before land window opens — water-first is better
	    int[] land5  = {10, 11};
	    int[] lDur5  = {1,   1};
	    int[] water5 = {0,   1};
	    int[] wDur5  = {2,   2};
	    System.out.println("Test 5: " + sol.earliestFinshTime(land5, lDur5, water5, wDur5));
	    // minWaterFinish=2 → land[0]: max(2,10)+1=11, land[1]: max(2,11)+1=12 → Water→Land=11
	    // minLandFinish=11 → water[0]: max(11,0)+2=13, water[1]: max(11,1)+2=13 → Land→Water=13
	    // Expected: 11

	    // Test 6: Single task each, exact overlap
	    int[] land6  = {5};
	    int[] lDur6  = {5};
	    int[] water6 = {5};
	    int[] wDur6  = {5};
	    System.out.println("Test 6: " + sol.earliestFinshTime(land6, lDur6, water6, wDur6));
	    // land finishes at 10 → water starts at max(10,5)=10, finishes at 15
	    // water finishes at 10 → land starts at max(10,5)=10, finishes at 15
	    // Expected: 15
	}
	
	

}
