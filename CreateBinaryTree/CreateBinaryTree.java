package CreateBinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
//import java.util.*;


/* 2196. Create Binary Tree From Descriptions
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] 
 * indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

Example 1:

Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.
 
Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.
 *
 * Time Complexity: O(n), n = number of descriptions (edges). Every step is a single linear pass, so overall O(n)
 * Space Complexity: O(n), All structures scale linearly with the number of descriptions, so space is O(n)
 */

public class CreateBinaryTree {
	
	// TreeNode definition
	static class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int val) { this.val = val;}
	}
	
	public TreeNode createBinaryTree(int[][] descriptions) {
		Map<Integer, TreeNode> nodeMap = new HashMap<>();
		Set<Integer> children = new HashSet<>();
		
		for (int[] desc : descriptions) {
			int parentVal = desc[0], childVal = desc[1];
			boolean isLeft = desc[2] == 1;
			
			nodeMap.putIfAbsent(parentVal, new TreeNode(parentVal));
			nodeMap.putIfAbsent(childVal, new TreeNode(childVal));
			
			if (isLeft) nodeMap.get(parentVal).left = nodeMap.get(childVal);
			else nodeMap.get(parentVal).right = nodeMap.get(childVal);
			
			children.add(childVal);
		}
		for(TreeNode node : nodeMap.values()) {
			if (!children.contains(node.val)) return node;
		}
		return null;
	}
	
	 // BFS level-order print to verify tree shape
    static String levelOrder(TreeNode root) {
        if (root == null) return "[]";
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(curr.val));
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }
        // Trim trailing nulls
        int last = result.size() - 1;
        while (last >= 0 && result.get(last).equals("null")) last--;
        return result.subList(0, last + 1).toString();
    }

    public static void main(String[] args) {
        CreateBinaryTree sol = new CreateBinaryTree();

        // Test 1:
        // descriptions: [20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]
        // Tree:
        //         50
        //        /  \
        //      20    80
        //     /  \   /
        //   15   17 19
        // Root = 50 (only node not in children set)
        // Expected level-order: [50, 20, 80, 15, 17, 19]
        int[][] d1 = {{20,15,1},{20,17,0},{50,20,1},{50,80,0},{80,19,1}};
        TreeNode r1 = sol.createBinaryTree(d1);
        System.out.println("Test 1 root: " + r1.val);
        System.out.println("Level-order: " + levelOrder(r1));
        System.out.println("Expected:    [50, 20, 80, 15, 17, 19]");

        // Test 2:
        // descriptions: [1,2,1],[2,3,0],[1,4,0]
        // Tree:
        //      1
        //     / \
        //    2   4
        //     \
        //      3
        // Root = 1
        // Expected level-order: [1, 2, 4, null, 3]
        int[][] d2 = {{1,2,1},{2,3,0},{1,4,0}};
        TreeNode r2 = sol.createBinaryTree(d2);
        System.out.println("\nTest 2 root: " + r2.val);
        System.out.println("Level-order: " + levelOrder(r2));
        System.out.println("Expected:    [1, 2, 4, null, 3]");

        // Test 3: single pair
        // descriptions: [5,3,1]
        // Tree:
        //    5
        //   /
        //  3
        // Root = 5
        // Expected level-order: [5, 3]
        int[][] d3 = {{5,3,1}};
        TreeNode r3 = sol.createBinaryTree(d3);
        System.out.println("\nTest 3 root: " + r3.val);
        System.out.println("Level-order: " + levelOrder(r3));
        System.out.println("Expected:    [5, 3]");
    }

}
