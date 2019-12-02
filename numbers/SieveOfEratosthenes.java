import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SieveOfEratosthenes {

    private boolean sieve[];
    private int limit;

    public SieveOfEratosthenes(int limit) {
        sieve = new boolean[limit + 1];
        this.limit = limit;
        for(int i = 0; i <= limit; i++) {
            sieve[i] = true;
        }
        checkPrimes();
    }

    private void checkPrimes() {
        for(int i = 2; i*i <= limit; i++) {
            if(sieve[i] == true) {
                for(int p = i*2; p <= limit; p += i) {
                    sieve[p] = false;
                }
            }
        }
        sieve[0] = sieve[1] = false;
    }

    private void displayPrimes() {
        System.out.println("\nThe prime numbers within the range are: ");
        for(int i = 0; i <= limit; i++) {
            if(sieve[i] == true) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int limit = 0;
        String input = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        
        do {
            
            System.out.print("Enter the limit to find primes within >> ");
            // input validation
            try {
                input = br.readLine();
                limit = Integer.parseInt(input);
            } catch(Exception e) {
                System.out.println(input + "is not a valid number. Try again. ");
            }

        } while(limit <= 0);

        SieveOfEratosthenes se = new SieveOfEratosthenes(limit);
        se.displayPrimes();
        System.out.println();
    }

}