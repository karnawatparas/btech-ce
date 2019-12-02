public class Factorial {

    // using static keyword, referencing/calling the function is possible without creating object 
    public static int RecursiveFactorial(int number) {
        if(number == 1) {
            return 1;
        }
        return number*RecursiveFactorial(number - 1);
    }

    public static int IterativeFactorial(int number) {
        int fact = 1;
        int temp = number;
        while(temp > 0) {
            fact = fact * temp;
            temp = temp - 1;
        }
        return fact;
    }

    public static void main(String[] args) {
        
        System.out.println("Factorial of 5 using recursion: " + RecursiveFactorial(5));
        System.out.println("Factorial of 5 using iteration: " + IterativeFactorial(5));

        System.out.println("Factorial of 7 using recursion: " + Factorial.RecursiveFactorial(7));
        System.out.println("Factorial of 7 using iteration: " + Factorial.IterativeFactorial(7));

    }

}