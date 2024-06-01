public class Grower {
    private InputInterpret input;
    public Grower(InputInterpret in) {
        this.input = in;
    }
    public void grow(){
        for (int i = 0; i < input.getA(); i++) {
            dupeDupedLine(i);
        }
    }
    private void dupeChar(char a) {
        for (int i = 0; i < input.getC(); i++) {
            System.out.print(a);
        }
    }
    private void dupeLine(int a){
        for (int i = 0; i < input.getB(); i++) {
            dupeChar(input.getIconChar(a,i));
        }
    }
    private void dupeDupedLine(int a){
        for (int i = 0; i < input.getC(); i++) {
            dupeLine(a);
            System.out.println();
        }
    }
}
