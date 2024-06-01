import java.util.Scanner;
public class Data {
    private int[][] req;
    private int[][] real;
    private int n;
    private int m;
    public Data(){
        Scanner reader = new Scanner(System.in);
        n = reader.nextInt();
        m = reader.nextInt();
        req = new int[n][2];
        real = new int[m][2];
        for (int i = 0; i < n; i++) {
            req[i][0] = reader.nextInt();
            req[i][1] = reader.nextInt();
        }
        for (int i = 0; i < m; i++) {
            real[i][0] = reader.nextInt();
            real[i][1] = reader.nextInt();
        }
        reader.close();
    }
    public int[][] getReq() {
        return req;
    }
    public int[][] getReal() {
        return real;
    }
    public int getM() {
        return m;
    }
    public int getN() {
        return n;
    }
    public int getReqD(int loc){
        return req[loc][0];
    }
    public int getReqS(int loc){
        return req[loc][1];
    }
    public int getRealD(int loc){
        return real[loc][0];
    }
    public int getRealS(int loc){
        return real[loc][1];
    }
}