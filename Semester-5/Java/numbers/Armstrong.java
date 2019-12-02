public class Armstrong {
    // demonstrate command line arguments
    // use java Armstrong <start> <end> to run
    public static void main(String args[]) {

        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);

        System.out.println("\nArmstrong numbers within the range " + start + " - " + end + " are as follows: ");

        for(int number = start; number <= end; number++) {

            int sum = 0;
            int original = 0;
            int digits = 0;

            original = number;

            while(original > 0) {
                digits++;
                original = original / 10;
            }

            original = number;

            while(original > 0) {
                int remainder = original % 10;
                sum = sum + (int) Math.pow((double)remainder, (double)digits);
                original = original / 10;
            }

            if(number == sum) {
                System.out.print(number + " ");
            }
        }

        System.out.println("\n");

    }

}