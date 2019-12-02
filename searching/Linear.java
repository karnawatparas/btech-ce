public class Linear {

    private static int array[] = {
        -19, 123, 1656, 9756, 98, 1023, 102, 99, 0, -81, 81, 72, 43
    };

    public static int search(int x) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int x1 = 81;
        int x2 = -9;

        int res = Linear.search(x1);
        if(res != -1) {
            System.out.println(x1 + " found at " + res);
        } else {
            System.out.println(x1 + " not found");
        }

        res = Linear.search(x2);
        if(res != -1) {
            System.out.println(x2 + " found at " + res);
        } else {
            System.out.println(x2 + " not found");
        }

    }

}