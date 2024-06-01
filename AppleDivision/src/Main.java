import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Integer> right = new LinkedList<>();
    static LinkedList<Integer> left = new LinkedList<>();
    static int minDif = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int amount = input.nextInt();
        //Takes in input
        for (int i = 0; i < amount; i++) {
            if ((i & 1)==1){
                right.add(input.nextInt());
            } else {
                left.add(input.nextInt());
            }
        }
        boolean continueShifting = true;
        boolean dir = true; //shift towards right = true
        while(continueShifting){ //shift phase
            int difference = difference();
            if (difference == 0) {
                minDif = 0;
                break;
            }
            System.out.println(left.toString()+" "+right.toString()+" "+difference);
            //If difference is negative then move element to the right
            dir = (difference < 0); //dir is true if diff negative
            if (dir) {
                if ((-difference) < minLeft()*2) {
                    continueShifting = false;
                } else {
                    shiftRight(smallestLessThanDif(difference));
                }
            } else {
                if (difference< minRight()*2) {
                    continueShifting = false;
                } else {
                    shiftLeft(smallestLessThanDif(difference));
                }
            }
        }
        continueShifting = true;
        while (continueShifting){
            int prevDiff = difference();
            swap(difference());
            System.out.println(left.toString()+" "+right.toString()+" "+difference());
            if (prevDiff==difference()) continueShifting = false;
        }
        System.out.println(difference());

    }
    public static int difference(){
        return sum(right)-sum(left);
    }
    public static int minRight(){
        int min = Integer.MAX_VALUE;
        for(Integer element: right){
            min = Math.min(min,element);
        }
        return min;
    }
    public static int minLeft(){
        int min = Integer.MAX_VALUE;
        for(Integer element: left){
            min = Math.min(min,element);
        }
        return min;
    }
    public static int sum(LinkedList<Integer> list){
        int count = 0;
        for(Integer element: list) count+=element;
        return count;
    }
    public static void shiftLeft(int value){
        left.add(value);
        right.remove(Integer.valueOf(value));
    }
    public static void shiftRight(int value){
        right.add(value);
        left.remove(Integer.valueOf(value));
    }
    public static void swap(SwapPair swapPair){
        if (Math.abs(difference())>(swapPair.getDiff()+difference())){
            shiftLeft(swapPair.getFirst());
            shiftRight(swapPair.getSecond());
        }
    }
    public static int smallestLessThanDif(int difference){
        int goal = Math.abs(difference / 2);
        int result = 0;
        if(difference<0){
            for (Integer element: left) {
                if (element > goal) continue;
                result = Math.max(result, element);
            }
        } else {
            for (Integer element: right) {
                if (element > goal) continue;
                result = Math.max(result, element);
            }
        }
        return result;
    }
    public static void swap(int difference){
        LinkedList<SwapPair> maxPairs = new LinkedList<>();
        for (Integer rElement: right) {
            LinkedList<SwapPair> pairs = new LinkedList<>();
            for (Integer lElement : left) {
                pairs.add(new SwapPair(rElement, lElement));
            }
            maxPairs.add(findBest(pairs, difference));
        }
        swap(findBest(maxPairs,difference));
    }
    private static SwapPair findBest(LinkedList<SwapPair> list, int difference){
        SwapPair max = new SwapPair(0, (int)Math.pow(2,20));
        for (SwapPair pair: list) {
            if (Math.abs(pair.getDiff()-difference)<Math.abs(max.getDiff()-difference)){
                max = new SwapPair(pair);
            }
        }
        return max;
    }
}