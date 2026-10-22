package LexGreaterPermutation;

package LexGreaterPermutationII;

/* 3734. Lexicographically Smallest Palindromic Permutation Greater Than Target
You are given two strings s and target, each of length n, consisting of lowercase English letters.

Return the lexicographically smallest string that is both a palindromic permutation of s and strictly greater than target. If no such permutation exists, return an empty string.

 
Example 1:

Input: s = "baba", target = "abba"

Output: "baab"

Explanation:

The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
The lexicograpahically smallest permutation that is strictly greater than target is "baab".
Example 2:

Input: s = "baba", target = "bbaa"

Output: ""

Explanation:

The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
None of them is lexicographically strictly greater than target. Therefore, the answer is "".
Example 3:

Input: s = "abc", target = "abb"

Output: ""

Explanation:

s has no palindromic permutations. Therefore, the answer is "".

Example 4:

Input: s = "aac", target = "abb"

Output: "aca"

Explanation:

The only palindromic permutation of s is "aca".
"aca" is strictly greater than target. Therefore, the answer is "aca".
 

Constraints:

1 <= n == s.length == target.length <= 300
s and target consist of only lowercase English letters.
 * 
 * Time Complexity: O(N²), Where $N$ is the length of strings s and target. During the divergence check, we run an outer loop up to $N/2$ times. 
 * 			Inside this loop, checking if the current prefix is formable takes up to $N/2$ iterations. Thus, operations cap at around ${(N/2)²}/{2}$. 
 * 			For the constraint $N ≤ 300$, this guarantees fewer than $12,000$ operations in the worst case scenario, making it nearly instantaneous.
 * Space Complexity: O(N), Constructing our answer safely sidesteps variable allocation overhead by using a few fixed-size frequency arrays (length 26) mapped 
 * 			to alphabet letters, along with a symmetric character array (pArr) scaling with the strict size $N$ needed for string formulation.
 */

public class LexGreaterPermutationII {
	
	public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        
        // 1. Tally character frequencies
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) (i + 'a');
            }
        }
        
        // If there's more than one odd frequency, a palindrome is impossible
        if (oddCount > 1) {
            return "";
        }
        
        // The pool of characters available for the first half
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }
        
        int L = n / 2;
        
        // 2. Try to exactly match the first L characters of the target
        boolean canFormExact = true;
        int[] currentFreq = new int[26];
        for (int i = 0; i < L; i++) {
            int c = target.charAt(i) - 'a';
            currentFreq[c]++;
            if (currentFreq[c] > halfFreq[c]) {
                canFormExact = false;
                break;
            }
        }
        
        if (canFormExact) {
            char[] pArr = new char[n];
            for (int i = 0; i < L; i++) {
                pArr[i] = target.charAt(i);
                pArr[n - 1 - i] = target.charAt(i);
            }
            if (n % 2 != 0) pArr[L] = midChar;
            
            String pStr = new String(pArr);
            // If the natural mirrored palindrome is strictly greater, it's the optimal choice
            if (pStr.compareTo(target) > 0) {
                return pStr;
            }
        }
        
        // 3. Try to diverge at index i in the first half (moving right to left)
        for (int i = L - 1; i >= 0; i--) {
            currentFreq = new int[26];
            boolean canFormPrefix = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                currentFreq[c]++;
                if (currentFreq[c] > halfFreq[c]) {
                    canFormPrefix = false;
                    break;
                }
            }
            
            if (!canFormPrefix) continue;
            
            // Calculate remaining available characters for the divergence point and beyond
            int[] remFreq = new int[26];
            for (int j = 0; j < 26; j++) {
                remFreq[j] = halfFreq[j] - currentFreq[j];
            }
            
            // Look for the smallest available character strictly greater than target[i]
            char bestC = 0;
            for (int j = target.charAt(i) - 'a' + 1; j < 26; j++) {
                if (remFreq[j] > 0) {
                    bestC = (char) (j + 'a');
                    break;
                }
            }
            
            if (bestC != 0) {
                char[] pArr = new char[n];
                
                // Copy the matched prefix
                for (int j = 0; j < i; j++) {
                    pArr[j] = target.charAt(j);
                    pArr[n - 1 - j] = target.charAt(j);
                }
                
                // Apply the divergence character
                pArr[i] = bestC;
                pArr[n - 1 - i] = bestC;
                remFreq[bestC - 'a']--;
                
                // Fill remaining slots in alphabetical order for the smallest permutation
                int idx = i + 1;
                for (int j = 0; j < 26; j++) {
                    while (remFreq[j] > 0) {
                        pArr[idx] = (char) (j + 'a');
                        pArr[n - 1 - idx] = (char) (j + 'a');
                        idx++;
                        remFreq[j]--;
                    }
                }
                
                if (n % 2 != 0) pArr[L] = midChar;
                
                return new String(pArr);
            }
        }
        
        // If we backtrack completely and find nothing, no valid permutation exists.
        return "";
    }

	public static void main(String[] args) {
        LexGreaterPermutation sol = new LexGreaterPermutation();

        // Example 1
        String s1 = "abc", target1 = "bba";
        System.out.println("Input: s = \"" + s1 + "\", target = \"" + target1 + "\"");
        System.out.println("Output: \"" + sol.lexGreaterPermutation(s1, target1) + "\""); // Expected: "bca"
        System.out.println("-----------------------------------");

        // Example 2
        String s2 = "leet", target2 = "code";
        System.out.println("Input: s = \"" + s2 + "\", target = \"" + target2 + "\"");
        System.out.println("Output: \"" + sol.lexGreaterPermutation(s2, target2) + "\""); // Expected: "eelt"
        System.out.println("-----------------------------------");

        // Example 3
        String s3 = "baba", target3 = "bbaa";
        System.out.println("Input: s = \"" + s3 + "\", target = \"" + target3 + "\"");
        System.out.println("Output: \"" + sol.lexGreaterPermutation(s3, target3) + "\""); // Expected: ""
    }
}

