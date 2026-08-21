package AsteroidDestroyed;

import java.util.Arrays;

/* 2126. Destroying Asteroids
 * You are given an integer mass, which represents the original mass of a planet. You are further given an integer array asteroids, where asteroids[i] 
 * is the mass of the ith asteroid.
 * You can arrange for the planet to collide with the asteroids in any arbitrary order. If the mass of the planet is greater than or equal to 
 * the mass of the asteroid, the asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the planet is destroyed.
 * Return true if all asteroids can be destroyed. Otherwise, return false.


Example 1:

Input: mass = 10, asteroids = [3,9,19,5,21]
Output: true
Explanation: One way to order the asteroids is [9,19,5,3,21]:
- The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
- The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
- The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
- The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
- The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
All asteroids are destroyed.

Example 2:

Input: mass = 5, asteroids = [4,9,23,4]
Output: false
Explanation: 
The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
This is less than 23, so a collision would not destroy the last asteroid.


Constraints:

1 <= mass <= 105
1 <= asteroids.length <= 105
1 <= asteroids[i] <= 105
 * 
 * Time Complexity: O(n log n), bottlenecked entirely by the sort.
 * Space Complexity:O(log n), nearly in-place, just the sort's recursion stack.
 * 
 * Why the Greedy Works
 * Sorting first ensures you always absorb the smallest available asteroid first. If you can't
 * absorb the smallest remaining one, you definitely can't absorb anything larger — so returning
 * false immediately is safe. The long cast on currentMass is a critical detail since summing
 * many int-range asteroids can easily overflow a 32-bit integer.
 */

public class AsteroidDestroyed {
	
	public boolean asteroidDestroyed(int mass, int[] asteroids) {
		Arrays.sort(asteroids);
		long currentMass = mass; //Prevent overflow!
		for (int asteroid : asteroids) {
			if (currentMass < asteroid) {
				return false;
			}
			currentMass += asteroid;
		}
		return true;
	}

	public static void main(String[] args) {
	    AsteroidDestroyed solver = new AsteroidDestroyed();

	    // Test 1: mass can absorb all asteroids
	    int mass1 = 10;
	    int[] asteroids1 = {3, 9, 19, 5, 21};
	    System.out.println("Test 1: " + solver.asteroidDestroyed(mass1, asteroids1));
	    // Sorted: [3,5,9,19,21]
	    // 10>=3 → 13, 13>=5 → 18, 18>=9 → 27, 27>=19 → 46, 46>=21 → 67
	    // Expected: true

	    // Test 2: mass gets blocked early
	    int mass2 = 5;
	    int[] asteroids2 = {4, 9, 23, 4};
	    System.out.println("Test 2: " + solver.asteroidDestroyed(mass2, asteroids2));
	    // Sorted: [4,4,9,23]
	    // 5>=4 → 9, 9>=4 → 13, 13>=9 → 22, 22<23 → STOP
	    // Expected: false

	    // Test 3: mass equals first asteroid exactly
	    int mass3 = 1;
	    int[] asteroids3 = {1, 1, 1};
	    System.out.println("Test 3: " + solver.asteroidDestroyed(mass3, asteroids3));
	    // 1>=1 → 2, 2>=1 → 3, 3>=1 → 4
	    // Expected: true

	    // Test 4: single asteroid too large
	    int mass4 = 1;
	    int[] asteroids4 = {2};
	    System.out.println("Test 4: " + solver.asteroidDestroyed(mass4, asteroids4));
	    // 1 < 2 → STOP
	    // Expected: false

	    // Test 5: large values (overflow check)
	    int mass5 = 100000;
	    int[] asteroids5 = {100000, 100000, 100000, 100000};
	    System.out.println("Test 5: " + solver.asteroidDestroyed(mass5, asteroids5));
	    // Expected: true (currentMass is long, no overflow)
	}
}

