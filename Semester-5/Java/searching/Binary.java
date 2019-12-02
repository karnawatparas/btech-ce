public class Binary {

    public static int array[] = {
        -19, 123, 1656, 9756, 98, 1023, 102, 99, 0, -81, 81, 72, 43
    };

    public static int search(int low, int high, int x) {
        if(low <= high) {
           int mid = low + (high - 1)/2; 
           if(array[mid] == x) {
               return mid;
           } else if(array[mid] < x) {
               return search(mid + 1, high, x);
           } else {
               return search(low, mid - 1, x);
           }
        }
        return -1;
    }

    public static void main(String[] args) {
        int x1 = 81;
        int x2 = -9;

        int res = Binary.search(0, Binary.array.length, x1);
        if(res != -1) {
            System.out.println(x1 + " found at " + res);
        } else {
            System.out.println(x1 + " not found");
        }

        res = Binary.search(0, Binary.array.length, x1);
        if(res != -1) {
            System.out.println(x2 + " found at " + res);
        } else {
            System.out.println(x2 + " not found");
        }

    }

}