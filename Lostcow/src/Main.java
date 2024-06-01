import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws Exception{
        File input = new File("src/lostcow.in");
        Scanner iReader = new Scanner(input);
        int x = iReader.nextInt();
        int y = iReader.nextInt();
        System.out.print(x+" "+y);
        int displacement = y-x;
        int distance=0;
        if(displacement>0){
            distance = countDis(toRight(displacement));
            distance += displacement - countDisPoint(toRight(displacement));
        } else if (displacement<0) {
            distance = countDis(toLeft(displacement));
            distance += countDisPoint(toLeft(displacement)) - displacement;
        }
        System.out.print("\n"+distance);
    }
    public static int countDis(int times){
        return (int) ((3*Math.pow(2,times)-4)/2);
        //returns total distance covered during complete journeys
    }
    public static int countDisPoint(int times){
        return (int) (-1*Math.pow(-1,(times&1))*Math.pow(2,(times-1)));
        //returns where farmer is after last complete journey
    }
    public static int toRight(int d){
        return (int) ((2*(Math.log(d-0.1)/Math.log(4)))+2);
        //returns amount of complete steps taken
    }
    public static int toLeft(int d){
        return (int) ((2*(Math.log((-d/2.0)-0.1)/Math.log(4)))+3);
        //returns amount of complete steps taken
    }
}