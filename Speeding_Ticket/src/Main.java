public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Tester tester = new Tester(data);
        System.out.print(tester.run());
    }
}