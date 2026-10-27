package CountDaysTogether;

/* 2409. Count Days Spent Together
Alice and Bob are traveling to Rome for separate business meetings.

You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.

Return the total number of days that Alice and Bob are in Rome together.

You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].

 

Example 1:

Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
Output: 3
Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.
Example 2:

Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
Output: 0
Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.
 

Constraints:

All dates are provided in the format "MM-DD".
Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
The given dates are valid dates of a non-leap year.
 * 
 * Time Complexity: O(1), Converting a "MM-DD" date string to an absolute day number takes a constant number of operations (looping through at most 12 months). 
 * 			Finding the maximum/minimum and calculating the overlap interval also takes constant time ${O}(1)$.
 * Space Complexity: O(1), Only a few primitive integer variables and a fixed-size month array of length 12 are allocated, requiring constant auxiliary memory.
 */ 

public class CountDaysTogether {

	public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
		int arriveAliceDay = convertToDays(arriveAlice);
		int leaveAliceDay = convertToDays(leaveAlice);
		int arriveBobDay = convertToDays(arriveBob);
		int leaveBobDay = convertToDays(leaveBob);
		
		// The overlap start is the later of the two arrival dates
		int startOverlap = Math.max(arriveAliceDay, arriveBobDay);
		// The overlap end is the earlier of the two leaving dates
		int endOverlap = Math.min(leaveAliceDay, leaveBobDay);
		
		// If startOverlap <= endOverlap, they overlap; otherwise, 0 days together
		return Math.max(0, endOverlap - startOverlap + 1);
	}
	
	private int convertToDays(String date) {
		int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int month = Integer.parseInt(date.substring(0, 2));
		int day = Integer.parseInt(date.substring(3, 5));
		
		int totalDays = day;
		for (int i = 0; i < month - 1; i++) {
			totalDays += daysInMonths[i];
		}
		
		return totalDays;
	}
	
	public static void main(String[] args) {
		CountDaysTogether sol = new CountDaysTogether();

		// Example 1
		String arriveAlice1 = "08-15", leaveAlice1 = "08-18";
		String arriveBob1 = "08-16", leaveBob1 = "08-19";
		System.out.println("Output 1: " + sol.countDaysTogether(arriveAlice1, leaveAlice1, arriveBob1, leaveBob1)); 
		// Expected: 3
		System.out.println("-----------------------------------");

		// Example 2
		String arriveAlice2 = "10-01", leaveAlice2 = "10-31";
		String arriveBob2 = "11-01", leaveBob2 = "12-31";
		System.out.println("Output 2: " + sol.countDaysTogether(arriveAlice2, leaveAlice2, arriveBob2, leaveBob2)); 
		// Expected: 0
	}
}
