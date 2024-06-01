import java.io.*;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        File input = new File("src/blocks.in");
        FileReader raw = new FileReader(input);
        char[][][] data = raw.getData();
        @SuppressWarnings("unchecked")
        HashMap<Character,Integer>[] maps = new HashMap[2];
        maps[0] = new HashMap<>();
        maps[1] = new HashMap<>();
        for (int i = 0; i < raw.getLen(); i++) {
            for(char linSeg : data[0][i]){
                maps[0].put(linSeg, maps[0].getOrDefault(linSeg, 0) + 1);
            }
            for(char linSeg : data[1][i]){
                maps[1].put(linSeg, maps[1].getOrDefault(linSeg, 0) + 1);
            }
            HashMapExt.mergeAdd(maps[0],maps[1]);
            HashMapExt.printAlphabet(); //just looks cool
            maps[0].clear();
            maps[1].clear();
        }
        HashWriter.writeHashMapToFile(HashMapExt.alphabet);
    }
}