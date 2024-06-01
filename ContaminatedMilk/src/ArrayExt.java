import java.util.Arrays;
import java.util.Comparator;



public final class ArrayExt {
    public static void sortCol(int[][] consumption, int index){
        Arrays.sort(consumption, Comparator.comparingInt(arr -> arr[index]));
    }
    public static int[] findSharedMilks(int[][] sickProfiles, int[] sick) throws InputFormatException {
        int milkAmt = sickProfiles[0].length;
        int people = sickProfiles.length;
        int length = milkAmt;
        boolean[] sharedMilks = new boolean[milkAmt];
        Arrays.fill(sharedMilks, true); // Initialize the sharedMilks array with 1 (shared by all).
        for (int person = 0; person < people; person++) { //iterates through all people
            if (sick[person]==0) continue; //if person not sick
            for(int milk = 0; milk<milkAmt; milk++){
                if (!sharedMilks[milk]) continue;
                if (sickProfiles[person][milk]<=0) {
                    sharedMilks[milk] = false;
                    length--; //reduce amount of possible milks by 1
                }
            }
        }
        if (length <= 0) throw new InputFormatException("no valid milks, length is recorded as: "+length);
        int[] ret = new int[length];
        int currentI = 0;
        for (int i = 0; i < milkAmt; i++) {
            if (sharedMilks[i]){
                ret[currentI++]=i;
            }
        }
        return ret;
    }
    public static int findMaxCount(int[] candidates, int[][] consumption, int amtPeople){
        int maxCount=0;
        for(int candidate: candidates){
            maxCount = Math.max(maxCount, countAmt(consumption,candidate,amtPeople));
        }
        return maxCount;
    }
    private static int countAmt(int[][] consumption, int milk, int amtPeople){
        boolean[] didDrink = new boolean[amtPeople];
        for (int[] drink: consumption){
            if (drink[1]==milk) didDrink[drink[0]] = true;
            //sets person index to true if person drank the milk
        }
        return countTrue(didDrink);
    }
    private static int countTrue(boolean[] booleans){
        int count=0;
        for(boolean b: booleans) if (b) count++;
        return count;
    }
}
class InputFormatException extends Exception{
    public InputFormatException(String e){
        super(e);
    }
    public InputFormatException(){
        super();
    }
}
