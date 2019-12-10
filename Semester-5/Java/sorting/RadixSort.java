public class RadixSort {

    private int arr[];

    private int bucket[][];
    private int count[];

    public RadixSort(int arr[]) {
        this.arr = arr;
        bucket = new int[10][10];
        count = new int[10];
        sort();
    }

    private int getLargest() {
        int largest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (largest < arr[i]) {
                largest = arr[i];
            }
        }
        return largest;
    }

    private void sort() {

        int size = arr.length;
        int largest = getLargest();

        int numberOfPasses = 0;

        while (largest != 0) {
            numberOfPasses++;
            largest = largest / 10;
        }

        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        int divisor = 1;

        for (int i = 0; i < numberOfPasses; i++) {

            for (int j = 0; j < size; j++) {
                int remainder = (arr[j] / divisor) % 10;
                bucket[remainder][count[remainder]] = arr[j];
                count[remainder]++;
            }

            System.out.println("\nBuckets: ");
            for (int row = 0; row < 10; row++) {
                System.out.print(row + " | ");
                for (int col = 0; col < 10; col++) {
                    System.out.print(bucket[row][col] + " ");
                }
                System.out.println();
            }

            int index = 0;
            for (int rem = 0; rem < 10; rem++) {
                int start = 0;
                while (start < count[rem]) {
                    arr[index] = bucket[rem][start];
                    bucket[rem][start] = 0;
                    start++;
                    index++;
                }
                count[rem] = 0;
            }

            divisor = divisor * 10;
        }

    }

    public static void main(String[] args) {
        int arr[] = { 999, 800, 75, 6, 551, 28, 707, 4, 303, 102, 99, 19, 29 };

        System.out.print("\nBefore sorting >> [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");

        new RadixSort(arr);

        System.out.print("\nAfter sorting >> [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

}