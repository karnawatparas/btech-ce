public class FloydTriangle {

    public static void printFloyd(int rows) {

        int number = 1;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j<= i; j++) {
                System.out.print(number + " ");
                number++;
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        System.out.println("\nFloyd's triangle for 5 rows : \n");
        FloydTriangle.printFloyd(5);
        System.out.println("\nFloyd's triangle for 10 rows : \n");
        FloydTriangle.printFloyd(10);

        System.out.println();

    }

}