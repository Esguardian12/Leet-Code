package AngleClock;

/* 1344. Angle Between Hands of a Clock
Medium

Hint
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

 
Example 1:


Input: hour = 12, minutes = 30
Output: 165
Example 2:


Input: hour = 3, minutes = 30
Output: 75
Example 3:


Input: hour = 3, minutes = 15
Output: 7.5
 

Constraints:

1 <= hour <= 12
0 <= minutes <= 59
 * 
 * Time Complexity: O(1), The method performs a fixed number of arithmetic operations (+, *, abs, comparisons). No loops or recursion are involved.
 * 			Execution time does not depend on the input values.
 * Space Complexity: O(1), Only a few variables (hourAngle, minuteAngle, angle) are used. No additional data structures are allocated.
 *          Memory usage remains constant regardless of input size.
 */

public class AngleClock {
	
	public double angleClock(int hour, int minutes) {
		// Handle 12 o'clock as 0 for position calculation
		if (hour == 12) {
			hour = 0;
		}
		
		// Calculate positions relative to 12 o'clock (0 degrees)
		double hourAngle = (hour * 30.0) + (minutes * 0.5);
		double minuteAngle = minutes * 6.0;
		
		// Find the absolute difference
		double angle = Math.abs(hourAngle - minuteAngle);
		
		// Return the smaller (inner) angle
		return angle > 180.0 ? 360.0 - angle : angle;
	}

	public static void main(String[] args) {
        AngleClock solution = new AngleClock();

        System.out.println("12:30 -> " + solution.angleClock(12, 30)); // 165.0
        System.out.println("3:30  -> " + solution.angleClock(3, 30));  // 75.0
        System.out.println("3:15  -> " + solution.angleClock(3, 15));  // 7.5
    }
}
