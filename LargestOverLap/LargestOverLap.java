package LargestOverLap;

package leetCode;

import java.util.ArrayList;
import java.util.List;

/* 835. Image Overlap
You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.

We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.

Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.

Return the largest possible overlap.

 
Example 1:


Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
Output: 3
Explanation: We translate img1 to right by 1 unit and down by 1 unit.

The number of positions that have a 1 in both images is 3 (shown in red).

Example 2:

Input: img1 = [[1]], img2 = [[1]]
Output: 1
Example 3:

Input: img1 = [[0]], img2 = [[0]]
Output: 0
 

Constraints:

n == img1.length == img1[i].length
n == img2.length == img2[i].length
1 <= n <= 30
img1[i][j] is either 0 or 1.
img2[i][j] is either 0 or 1.
 * 
 * Time Complexity: O(N⁴), Where $N$ is the length of the matrix. In the worst-case scenario (matrices filled entirely with 1s), there are $N^2$ points in img1 
 * 			and $N^2$ points in img2. Iterating through all possible pairs takes $(N^2) \times (N^2) = N^4$ operations. Given the constraint $N \le 30$, 
 * 			the maximum number of operations is $30^4 = 810,000$, which executes in less than a millisecond.
 * Space Complexity: O(N²), We store the coordinates of the 1s, which takes up to ${O}(N^2)$ space for each list. The count array is fixed at size 
 * 			$2N \times 2N$, which also consumes ${O}(N^2)$ auxiliary space.
 */

public class LargestOverlap {
	
	public int largestOverlap(int[][] img1, int[][] img2) {
		int n = img1.length;
		List<int[]> onesImg1 = new ArrayList<>();
		List<int[]> onesImg2 = new ArrayList<>();
		
		// Step 1: Collect coordinates of all 1s in both images
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(img1[r][c] == 1) {
					onesImg1.add(new int[]{r, c});
				}
				if(img2[r][c] == 1) {
					onesImg2.add(new int[] {r, c});
				}
			}
		}
		
		// count array size is 2n x 2N to handle negative shifts by offsetting them by N
		int[][] count = new int[2 * n][2 * n];
		int maxOverlap = 0;
		
		// Step 2: Calculate translation vectors for every pair of 1s
		for(int[] p1 : onesImg1) {
			for(int[] p2 : onesImg2) {
				// shift coordinates by +n to avoid negative array indices
				int shiftR = p2[0] - p1[0] + n;
				int shiftC = p2[1] - p1[1] + n; 
				
				count[shiftR][shiftC]++;
				maxOverlap = Math.max(maxOverlap, count[shiftR][shiftC]);
			}
		}
		
		return maxOverlap;
	}
	
	public static void main(String[] args) {
        LargestOverlap sol = new LargestOverlap();
        
        // Example 1
        int[][] img1_1 = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
        };
        int[][] img2_1 = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 0, 1}
        };
        System.out.println("Output: " + sol.largestOverlap(img1_1, img2_1)); // Expected: 3
        System.out.println("-----------------------------------");
        
        // Example 2
        int[][] img1_2 = {{1}};
        int[][] img2_2 = {{1}};
        System.out.println("Output: " + sol.largestOverlap(img1_2, img2_2)); // Expected: 1
        System.out.println("-----------------------------------");
        
        // Example 3
        int[][] img1_3 = {{0}};
        int[][] img2_3 = {{0}};
        System.out.println("Output: " + sol.largestOverlap(img1_3, img2_3)); // Expected: 0
    }

}

