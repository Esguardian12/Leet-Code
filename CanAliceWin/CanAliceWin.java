package CanAliceWin;

/* 3360. Stone Removal Game
	Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.

	Alice starts by removing exactly 10 stones on her first turn.
	For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
	The player who cannot make a move loses the game.

	Given a positive integer n, return true if Alice wins the game and false otherwise.

	 

	Example 1:

	Input: n = 12

	Output: true

	Explanation:

	Alice removes 10 stones on her first turn, leaving 2 stones for Bob.
	Bob cannot remove 9 stones, so Alice wins.
	Example 2:

	Input: n = 1

	Output: false

	Explanation:

	Alice cannot remove 10 stones, so Alice loses.
	 

	Constraints:

	1 <= n <= 50
	create a main and tell me the time and space complexity ?
	Solution & main Method in Java
	In 3360. Stone Removal Game, moves have a fixed required size on each turn:

	Turn 1 (Alice): must remove 10 stones

	Turn 2 (Bob): must remove 9 stones

	Turn 3 (Alice): must remove 8 stones

	...and so on, decreasing by 1 each turn down to 1 stone (up to 10 total possible moves).

	Because every move's count is predetermined, we can simulate the game step-by-step and toggle whose turn it is until the current player has fewer stones left than required (n < remove). Whoever cannot complete their required move loses.
 * 
 */


public class CanAliceWin {

	    public boolean canAliceWin(int n) {
	        int remove = 10;
	        boolean aliceTurn = true;

	        while (n >= remove && remove > 0) {
	            n -= remove;
	            remove--;
	            aliceTurn = !aliceTurn;
	        }

	        // If the loop ends on Alice's turn, Alice cannot move -> she loses (false).
	        // If it ends on Bob's turn, Bob cannot move -> Alice wins (true).
	        return !aliceTurn;
	    }

	    public static void main(String[] args) {
	        CanAliceWin sol = new CanAliceWin();

	        int[] testCases = {1, 9, 10, 12, 19, 20, 50};
	        for (int n : testCases) {
	            System.out.println("n = " + n + " -> canAliceWin = " + sol.canAliceWin(n));
	        }
	    }
	}
//	Complexity Analysis
//	Time Complexity: 
//	O
//	(
//	1
//	)
//	O(1) — Even though there is a while loop, the move size remove starts at 10 and decreases by 1 each iteration (10, 9, 8, ..., 1). The loop runs at most 10 times regardless of n (and n <= 50 in the problem constraints), making the runtime constant.
//
//	Space Complexity: 
//	O
//	(
//	1
//	)
//	O(1) — Only a few primitive variables (remove, aliceTurn, and loop variables in main) are used, requiring constant extra memory.
//
//	Want to explore this problem further?
//
//	Show the closed-form math solution
//
//	Trace edge cases around n = 10 and 19
//	Skip to the latest Gemini result




