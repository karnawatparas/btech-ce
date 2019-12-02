import java.util.Scanner;

public class Prime {

    public static void main(String[] args) {

        boolean isPrime = true;
        int number;

        Scanner in = new Scanner(System.in);

        System.out.println();

        // input validation
        do {
            System.out.print("Enter a 'positive' number: ");
            // check whether the input is not a character/string/floating-point
            while(!in.hasNextInt()) {
                String input = in.next();
                System.out.format("\"%s\" is not a valid number. Enter again: ", input);
            }
            number = in.nextInt();
        } while(number < 0); // checks whether the input is positive

        in.close();

        System.out.println();

        // 1 is neither prime nor composite
        if(number == 1) {
            isPrime = false;
        }

        // naive algorithm to check for prime
        for(int i = 2; i <= number/2; i++) {
            if(number % i == 0) {
                isPrime = false;
                break;
            }
        }

        if(isPrime) {
            System.out.format("%d is a 'prime' number \n", number);
        } else {
            if(number == 1) {
                System.out.format("%d is a 'neither prime nor composite' number \n", number);
            } else {
                System.out.format("%d is a 'not a prime' number \n", number);
            }
        }

        System.out.println();

    }

}