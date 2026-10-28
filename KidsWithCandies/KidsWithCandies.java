package KidsWithCandies;

import java.util.*;

public class KidsWithCandies {
	
	 public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

	        int max = Integer.MIN_VALUE;

	        for (int i = 0; i < candies.length; i++) {
	            if (candies[i] > max) {
	                max = candies[i];
	            }
	        }

	        List<Boolean> result = new ArrayList<>();

	        for (int i = 0; i < candies.length; i++) {
	            if (candies[i] + extraCandies >= max) {
	                result.add(true);
	            } else {
	                result.add(false);
	            }
	        }

	        return result;
	    }

	    // MAIN METHOD
	    public static void main(String[] args) {

	        KidsWithCandies sol = new KidsWithCandies();

	        int[] candies = {2, 3, 5, 1, 3};
	        int extraCandies = 3;

	        List<Boolean> result =
	            sol.kidsWithCandies(candies, extraCandies);

	        System.out.println(result);
	    }

}

