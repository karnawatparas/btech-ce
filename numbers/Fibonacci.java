public class Fibonacci {

    public int recursiveHelper(int limit) {
        if(limit == 0) {
            return 0;
        } else if(limit == 1 || limit == 2) {
            return 1;
        } else {
            return recursiveHelper(limit - 1) + recursiveHelper(limit - 2);
        }
    }

    public void printSeriesRecursion(int limit) {
        System.out.println("\nFibonnaci series of max. " + limit + " numbers using recursive implementation is: ");
        for(int i = 0; i < limit; i++) {
            System.out.print(recursiveHelper(i) + " ");
        }
    }

    public void printSeriesIterative(int limit) {
        int first = 0;
        int second = 1;
        int third;
        System.out.println("\nFibonnaci series of max. " + limit + " numbers using iterative implementation is: ");
        
        System.out.print(first + " " + second + " ");
        third = first + second;
        int i = 2;

        while(i < limit) {
            System.out.print(third + " ");
            first = second;
            second = third;
            third = first + second;
            i++;
        }
    }

    public static void main(String[] args) {

        Fibonacci fb = new Fibonacci();

        fb.printSeriesRecursion(3);
        fb.printSeriesIterative(3);

        fb.printSeriesRecursion(10);
        fb.printSeriesIterative(10);

        System.out.println("\n");

    }

}