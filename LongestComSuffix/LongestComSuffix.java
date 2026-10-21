package LongestComSuffix;

package leetCode;

/* 3093. Longest Common Suffix Queries
 * You are given two arrays of strings wordsContainer and wordsQuery.
 * For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with wordsQuery[i]. 
 * If there are two or more strings in wordsContainer that share the longest common suffix, find the string that is the smallest in length. 
 * If there are two or more such strings that have the same smallest length, find the one that occurred earlier in wordsContainer.
 * Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest common suffix with wordsQuery[i].

Example 1:

Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]

Output: [1,1,1]

Explanation:

Let's look at each wordsQuery[i] separately:

For wordsQuery[0] = "cd", strings from wordsContainer that share the longest common suffix "cd" are at indices 0, 1, and 2. Among these, 
the answer is the string at index 1 because it has the shortest length of 3.
For wordsQuery[1] = "bcd", strings from wordsContainer that share the longest common suffix "bcd" are at indices 0, 1, and 2. 
Among these, the answer is the string at index 1 because it has the shortest length of 3.
For wordsQuery[2] = "xyz", there is no string from wordsContainer that shares a common suffix. Hence the longest common suffix is "", 
that is shared with strings at index 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.
Example 2:

Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]

Output: [2,0,2]

Explanation:

Let's look at each wordsQuery[i] separately:

For wordsQuery[0] = "gh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, 
the answer is the string at index 2 because it has the shortest length of 6.
For wordsQuery[1] = "acbfgh", only the string at index 0 shares the longest common suffix "fgh". Hence it is the answer, even though the 
string at index 2 is shorter.
For wordsQuery[2] = "acbfegh", strings from wordsContainer that share the longest common suffix "gh" are at indices 0, 1, and 2. Among these, 
the answer is the string at index 2 because it has the shortest length of 6.
 

Constraints:

1 <= wordsContainer.length, wordsQuery.length <= 104
1 <= wordsContainer[i].length <= 5 * 103
1 <= wordsQuery[i].length <= 5 * 103
wordsContainer[i] consists only of lowercase English letters.
wordsQuery[i] consists only of lowercase English letters.
Sum of wordsContainer[i].length is at most 5 * 105.
Sum of wordsQuery[i].length is at most 5 * 105.
 * 
 * Time Complexity: O(N*L + M*Q), Both phases are linear in total characters. 
 * Space Complexity: O(N*L), Dominated by the trie storage.
 * 
 * Key Design Notes :
 * Reverse insertion is what makes this suffix trie - inserting right-to-left means shared suffixes share trie paths.
 * minLen + idx at every node ensures that when a query traversal stops (no more matching children), the best answer is already
 *      stored at that node - no backtracking needed.
 * The root stores the globally shortest word, acting as the fallback when there's zero suffix overlap. 
 */
 class TrieNode {
	 TrieNode[] children;
	 int minLen;
	 int idx;
	 
	 TrieNode(){
		 children = new TrieNode[26];
		 minLen = Integer.MAX_VALUE;
		 idx = Integer.MIN_VALUE;
	 }
 }
 
 class Trie {
	 TrieNode root;
	 
	 Trie() {
		 root = new TrieNode();
	 }
	 
	 void insert(String s, int idx) {
		 TrieNode node = root;
		 if (s.length() < node.minLen) {
			 node.minLen = s.length();
			 node.idx = idx;
		 }
		 
		 for (int i = s.length() - 1; i >= 0; i--) {
			 int c = s.charAt(i) - 'a';
			 if (node.children[c] == null) {
				 node.children[c] = new TrieNode();
			 }
			 node = node.children[c];
			 
			 if (s.length() < node.minLen) {
				 node.minLen = s.length();
				 node.idx = idx;
			 }
		 }
	 }
 }



public class LongestComSuffix {
	
	public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
		Trie trie = new Trie();
		for (int i = 0; i < wordsContainer.length; i++) {
			trie.insert(wordsContainer[i], i);
		}
		
		int[] ans = new int[wordsQuery.length];
		for (int i = 0; i < wordsQuery.length; i++) {
			TrieNode node = trie.root;
			String query = wordsQuery[i];
			for (int j = query.length() - 1; j >= 0; j--) {
				int c = query.charAt(j) - 'a';
				if (node.children[c] != null) {
					node = node.children[c];
				} else {
					break;
				}
			}
			ans[i] = node.idx;
		}
		return ans;
	}
	
	public static void main(String[] args) {
	    LongestComSuffix solution = new LongestComSuffix();

	    // Test 1: Basic case
	    String[] wordsContainer1 = {"abcd", "bcd", "xbcd"};
	    String[] wordsQuery1 = {"cd", "bcd", "xyz"};
	    System.out.println("Test 1: " + java.util.Arrays.toString(
	        solution.stringIndices(wordsContainer1, wordsQuery1)
	    ));
	    // Expected: [1, 1, 1]
	    // "cd"  -> "bcd"(idx=1) and "xbcd"(idx=2) both share "cd", but "bcd" is shorter -> 1
	    // "bcd" -> "bcd"(idx=1) is exact match and shortest -> 1
	    // "xyz" -> no common suffix, root holds shortest word "bcd"(idx=1) -> 1

	    // Test 2: Multiple matches, tie-break by shortest then smallest index
	    String[] wordsContainer2 = {"leetcode", "code"};
	    String[] wordsQuery2 = {"code", "de"};
	    System.out.println("Test 2: " + java.util.Arrays.toString(
	        solution.stringIndices(wordsContainer2, wordsQuery2)
	    ));
	    // Expected: [1, 1]

	    // Test 3: No common suffix (falls back to root = shortest word)
	    String[] wordsContainer3 = {"hello", "hi", "hey"};
	    String[] wordsQuery3 = {"zzz"};
	    System.out.println("Test 3: " + java.util.Arrays.toString(
	        solution.stringIndices(wordsContainer3, wordsQuery3)
	    ));
	    // Expected: [1]  ("hi" is shortest at root)
	}

}
