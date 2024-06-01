import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
//variable declaration here
    static int N;
    static int C;
    static int P;
    static LinkedList<String> nouns = new LinkedList<>();
    static LinkedList<String> transitives = new LinkedList<>();
    static LinkedList<String> intransitives = new LinkedList<>();
    static LinkedList<String> conjunctions = new LinkedList<>();
    static StringBuilder result;
    static boolean conjoined;
    static int sentencesLeft;
    static int totalWords;

    public static void main(String[] args) throws IOException {
        FileWriter logWriter = new FileWriter("src/logs.txt");
        Scanner scanner = new Scanner(System.in);
        final int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            N = scanner.nextInt();
            C = scanner.nextInt();
            P = scanner.nextInt();
            scanner.nextLine();
            nouns.clear();
            transitives.clear();
            intransitives.clear();
            conjunctions.clear();
            logWriter.write("Variables successfully declared \n");
            for (int j = 0; j < N; j++) {
                String line = scanner.nextLine();
                String[] storage = line.trim().split(" ");
                logWriter.write(storage[0]+" "+storage[1]+"\n");
                assert storage.length == 2;
                switch (storage[1]){
                    case "noun" -> nouns.addLast(storage[0]);
                    case "transitive-verb" -> transitives.addLast(storage[0]);
                    case "intransitive-verb" -> intransitives.addLast(storage[0]);
                    case "conjunction" -> conjunctions.addLast(storage[0]);
                    default -> throw new IllegalArgumentException(
                            "string of type "+storage[1]+" does not match any possibilities");
                }
            }
            result = new StringBuilder();
            logWriter.write("data successfully ingested \n");
            sentencesLeft = Math.min(conjunctions.size(), P) + P;
            conjoined = false;
            totalWords = 0;
            while (sentencesLeft>0) {
                //if no more sentences can be made
                if (!canMakeSentence()) break;
                //if sentence type 1 can be produced
                if (!intransitives.isEmpty() && !nouns.isEmpty()) {
                    result.append(nouns.pollFirst()).append(" ").append(intransitives.pollFirst());
                    totalWords+=2;
                    attemptConjoin();
                    continue;
                }
                //if sentence of type 2 can be made
                if (nouns.size()>=2 && !transitives.isEmpty()){
                    result.append(nouns.pollFirst()).append(" ").append(transitives.pollFirst()).append(" ").append(nouns.pollFirst());
                    totalWords+=3;
                    if (!(nouns.size()>=2 && !transitives.isEmpty())||P==1){
                        commaNoun();
                    }
                    attemptConjoin();
                }
            }
            System.out.println(totalWords);
            System.out.println(result);

        }
        logWriter.close();
        scanner.close();
    }
    public static void attemptConjoin(){ //method joins sentences together
        if (conjunctions.isEmpty()){ //conjunctions no longer exist
            endSentence();
            return;
        }
        if (!conjoined){ //previous statement is not conjoined
            if(canMakeSentence()){ //valid because important parts of method call already fulfilled
                conjoinSentence();
                conjoined=true;
            } else {
                endSentence();
            }
        } else {
            conjoined=false;
            endSentence();
        }
    }
    public static void conjoinSentence(){
        result.append(" ").append(conjunctions.pollFirst()).append(" ");
        sentencesLeft--;
        totalWords++;
    }
    public static void endSentence(){
        result.append(".");
        P--;
        sentencesLeft--;
        if (canMakeSentence()){ //adds space if another sentence can be made
            result.append(" ");
        }
    }

    public static boolean canMakeSentence(){
        if (P<1) return false;
        if (nouns.size() >= 2 && !transitives.isEmpty()) return true; //sentence type 2
        if (!nouns.isEmpty() && !intransitives.isEmpty()) return true; //sentence type 1
        return false;
    }

    public static void commaNoun(){
        while (!nouns.isEmpty()&&C>0){
            result.append(", ").append(nouns.pollFirst());
            C--;
            totalWords++;
        }
    }
}