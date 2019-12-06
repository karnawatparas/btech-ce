import java.util.Scanner;

public class Sort {
    public static String stringSort(String s) {
        if(s.length() < 2) {
            return s;
        } else {
            char min = s.charAt(0);
            int position = 0;

            int length = s.length();

            for(int i = 1; i < length; i++) {
                if(min > s.charAt(i)) {
                    min = s.charAt(i);
                    position = i;
                }
            }
            return min + stringSort(s.substring(0, position) + s.substring(position + 1));
        }
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string >> ");
        String input = in.nextLine();
        String sorted = stringSort(input);
        System.out.format("Original - %s , Sorted - %s \n", input, sorted);
        in.close();
    }
}