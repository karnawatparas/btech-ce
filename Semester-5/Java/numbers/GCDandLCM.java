public class GCDandLCM {

    public static int findGCD(int a, int b) {
        if (a == 0) {
            return b;
        }
        return findGCD((b % a), a);
    }

    public static int findGCDIterative(int a, int b) {

        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static int findLCM(int a, int b) {
        return ((a * b) / findGCD(a, b));
    }

    public static void main(String[] args) {

        System.out.println("GCD of 15 and 20 is: " + findGCD(15, 20));
        System.out.println("GCD of 15 and 20 (iterative) is: " + findGCDIterative(15, 20));
        System.out.println("LCM of 15 and 20 is: " + findLCM(15, 20));
        System.out.println("GCD of 1 and 13 is: " + findGCD(1, 13));
        System.out.println("GCD of 1 and 13 (iterative) is: " + findGCDIterative(1, 13));
        System.out.println("LCM of 1 and 13 is: " + findLCM(1, 13));

    }

}