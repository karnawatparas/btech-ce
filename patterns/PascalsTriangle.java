public class PascalsTriangle {

    public static void main(String[] args) {
        int rows = Integer.parseInt(args[0]);

        for(int i = 1; i <= rows; i++) {

            for(int k = 1; k <= rows - i; k++) {
                System.out.print(" ");
            }

            int value = 1;
            for(int j = 1; j <= i; j++) {
                System.out.print(value + " ");
                value = value * (i - j)/j;
            }
            System.out.println();
        }

    }

}