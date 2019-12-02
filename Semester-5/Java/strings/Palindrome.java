import java.util.Scanner;

public class Palindrome {

    private String string;

    public Palindrome(String str) {
        string = str;
    }

    private String reverse() {
        String rev = "";
        for(int i = string.length() - 1; i >= 0; i--) {
            rev = rev + string.charAt(i);
        }
        return rev;
    }

    public boolean isPalindrome() {
        return reverse().equals(string);
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        // keep taking inputs
        // enter exit to terminate the program
        while(!in.hasNext("exit")) {
            input = in.next();
            System.out.println("Palindrome? " + new Palindrome(input).isPalindrome());
        }

        in.close();

    }

}