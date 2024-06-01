public class Main {
    public static void main(String[] args) {
        String possibilities = "qweyuiopfghjkzxcvbnm";
        char[] possibles = possibilities.toCharArray();
        int len = possibilities.length();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len+1; j++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("a").append(possibles[i]).append((j!=len)?possibles[j]:"a").append("el");
                System.out.println(stringBuilder);
            }
        }
    }
}