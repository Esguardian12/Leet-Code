package AverageSalary;

/* 1491. Average Salary Excluding the Minimum and Maximum Salary
 * You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
 * Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.

Example 1:

Input: salary = [4000,3000,1000,2000]
Output: 2500.00000
Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
Example 2:

Input: salary = [1000,2000,3000]
Output: 2000.00000
Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
 
Constraints:

3 <= salary.length <= 100
1000 <= salary[i] <= 106
All the integers of salary are unique.
 *
 * Time Complexity: O(n), You traverse the array once: Finding min, max and sum -> O(n)  
 * Space Complexity: O(1), You only use a few variables(min, max, sum):
 */

public class AverageSalary {
	
	public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int s : salary) {
            sum += s;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }

        // remove min and max
        sum = sum - min - max;

        return (double) sum / (salary.length - 2);
    }

    public static void main(String[] args) {

        AverageSalary obj = new AverageSalary();

        int[] salary1 = {4000, 3000, 1000, 2000};
        int[] salary2 = {1000, 2000, 3000};
        int[] salary3 = {6000, 5000, 4000, 3000, 2000};

        System.out.println(obj.average(salary1)); // 2500.0
        System.out.println(obj.average(salary2)); // 2000.0
        System.out.println(obj.average(salary3)); // 4000.0
    }

}

