import java.util.Scanner;

public class Substring {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String choice;

        System.out.println("\n> Enter exit at '>' to terminate the program");

        do {
            System.out.print("Enter the text >> ");
            String text = sc.nextLine();
            System.out.print("Enter the substring >> ");
            String pattern = sc.nextLine();

            int occurences = 0;
            int textLength = text.length();
            int patternLength = pattern.length();

            for(int i = 0; i <= textLength - patternLength; i++) {

                int j;
                for(j = 0; j < patternLength; j++) {

                    if(text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }

                }

                if(j == patternLength) {
                    if(occurences == 0) {
                        System.out.print("Occurence index/indices >> ");
                    }
                    occurences++;
                    System.out.print(i + " ");
                }

            }

            System.out.println("\nNumber of occurences : " + occurences + "\n");

            System.out.print(" > ");
            choice = sc.nextLine();
        } while(!choice.equalsIgnoreCase("exit"));

        System.out.println();
        sc.close();
    
    }

}