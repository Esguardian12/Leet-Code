package FindNumWithEvenDigits;

public class FindNumsWithEvenDigits {

	 public int findNumbers(int[] nums) {
	        if (nums.length == 0){
	            return 0;
	        }
	        int count = 0;
	        for (int i = 0; i < nums.length; i++){
	            int numOfDigits = 0;
	            while ( nums[i] != 0){
	                nums[i] = nums[i]/10;
	                numOfDigits++;
	            }
	            if (numOfDigits % 2 == 0){
	                count++;
	            }
	        }
	        return count;
	    }
	 
	 public static void main(String[] args) {
	        FindNumsWithEvenDigits obj = new FindNumsWithEvenDigits();

	        int[] nums = {12, 345, 2, 6, 7896};

	        int result = obj.findNumbers(nums);

	        System.out.println("Numbers with even number of digits: " + result);
	    }
}
