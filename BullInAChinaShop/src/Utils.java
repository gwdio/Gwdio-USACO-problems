final public class Utils {
    public static boolean hash(char input) {
        return input == '#';
    }
    public static boolean[] hash(char[] input) {
        int length = input.length;
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            result[i]=hash(input[i]);
        }
        return result;
    }

}
