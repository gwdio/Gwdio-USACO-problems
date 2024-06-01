import java.util.Arrays;
import java.util.Comparator;

final public class CowArrays {
    public static boolean charToBool(char c){
        return c=='1';
    }
    public static void sortArr(int[][] arr){
        Arrays.sort(arr, Comparator.comparingInt(compare->compare[0]));
    }
    public static int cowIn(int[] cowInput, int[][] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new IllegalArgumentException("Invalid index.");
        }

        int value1 = arr[index][1];
        int value2 = arr[index][2];

        for (int i = 0; i < cowInput.length; i++) {
            if (cowInput[i] > 0 && (i == value1 || i == value2)) {
                return i; // Return the index of the triggering cow
            }
        }

        return -1; // If cow is not found
    }

    public static int otherVal(int[] meet, int search) {
        assert meet.length == 3;
        if (meet[1] == search) return meet[2];
        else return meet[1];
    }

    public static int maxK(int[] currentlySick) { //maximum achievable value for K
        int maxNegative = Integer.MIN_VALUE; // Initialize maxNegative to the smallest possible value

        for (int value : currentlySick) {
            if (value < 0 && value > maxNegative) {
                maxNegative = value; // Update maxNegative to the largest negative value found so far
            }
        }

        return maxNegative == Integer.MIN_VALUE ? Integer.MAX_VALUE : -maxNegative - 1;
    }

    public static int minK(int[] currentlySick) { //minimum achievable value for K
        int minPositive = Integer.MIN_VALUE; // Initialize minPositive to the smallest possible value

        for (int value : currentlySick) {
            if (value > minPositive) {
                minPositive = value; // Update minPositive to the largest positive value found so far
            }
        }

        return minPositive - 1;
    }

    public static String findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max == Integer.MAX_VALUE ? "Infinity" : String.valueOf(max);
    }

    public static String findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        return String.valueOf(min);
    }
}
