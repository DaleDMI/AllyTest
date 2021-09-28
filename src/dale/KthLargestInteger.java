package dale;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KthLargestInteger {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please supply a comma separated list of Integers and the requested kth largest.");
            System.exit(-99);
        }
        String listOfInts = args[0];
        int k = Integer.valueOf(args[1]);

        if (k > listOfInts.length()) {
            System.out.println("k must be less than or equal to the count of numbers.");
            System.exit(-98);
        }

        System.out.println("sequence: " + listOfInts + "\nk: " + k);
        int kthLargest = getKthLargestInteger(listOfInts, k);
        System.out.println("kth Largest: " + kthLargest);
    }

    // Uses dual-pivot quicksort
    private static int getKthLargestInteger(String listOfInts, int k) {
        List<Integer> arrOfInts = Arrays.stream(listOfInts.split(","))
                .map(Integer::valueOf)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        return arrOfInts.get(k - 1);
    }
}
