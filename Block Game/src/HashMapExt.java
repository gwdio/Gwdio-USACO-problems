import java.util.HashMap;
public class HashMapExt {
    static HashMap<Character,Integer> alphabet = new HashMap<>();
    private HashMapExt(){
    }
    public static void printAlphabet(){
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            sb.append(alphabet.getOrDefault(c, 0));
        }
        System.out.println(sb);
    }
    public static void mergeAdd(HashMap<Character, Integer> a, HashMap<Character, Integer> b){
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.put(c,alphabet.getOrDefault(c,0)
                    +Math.max(
                            a.getOrDefault(c,0),
                            b.getOrDefault(c,0)
            ));
        }
    }


}
