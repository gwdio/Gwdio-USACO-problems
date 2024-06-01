import java.util.Arrays;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
public class IsPrime {

    static Integer[] Primes = new Integer[4792];
    private static void PrimeList() throws IOException {
        File primeList = new File("src/Primes.txt");
        String primes = null;
        try{primes = Files.readString(primeList.toPath());}
        catch (IOException e){System.out.println("String read error");}
        try {
            String[] PrimeString = primes.split(", ");
            for (int i = 0; i < 4791; i++) {
                Primes[i] = Integer.parseInt(PrimeString[i]);
            }
        } catch (NullPointerException e) {System.out.println("Split broke");}

    }
    public static boolean isPrime(int num) throws IOException {
        PrimeList();
        if(Arrays.asList(Primes).contains(num)){return true;}
        for (Integer a: Primes){
            if ((num % a) == 0){return false;}
        }
        return true;
    }
}
