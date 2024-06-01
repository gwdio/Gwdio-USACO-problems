import java.util.Scanner;
import java.lang.Exception;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your number: ");
        String input = scanner.nextLine();
        System.out.print("What base is this currently in: ");
        int current = scanner.nextInt();
        int number = 0;
        if(current!=1) {
            try {
                number = Integer.parseInt(input, current);
            } catch (NumberFormatException e) {
                System.out.println("Number cannot be parsed as it contains illegal values");
                System.exit(1);
            }
        } else {
            int i = Math.abs(Integer.parseInt(input.trim())) * 9 + 1;
            if(Math.log10(i)-(int)(Math.log10(i)+.5)<0.0000001){
                if(input.charAt(0)=='-') number = -1*(input.length()-1);
                else if(input.charAt(0)=='+') number = input.length()-1;
                else number = input.length();
            } else throw new NumberFormatException("You're trying to use base 1 and you still can't");
        }
        scanner.nextLine();
        System.out.print("What base would you like to convert this into: ");
        int result = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Your converted result is: ");
        if(result!=1) System.out.println(Integer.toString(number,result));
        else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < number; i++) {
                stringBuilder.append(1);
            }
            System.out.println(stringBuilder);
        }

    }
}