public class BubbleSort {

    public BubbleSort() { 

    }

    public static void sort(int arr[]) {

        int n = arr.length;

        for(int i = 0; i < n - 1; i++) {

            for(int j = 0; j < n - i - 1; j++) {

                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }

        }

    }

    public static void main(String[] args) {

        int arr[] = {90, -10, 100, 23, 26, 81, 1, -19, 999, 1000, 123, 123};

        System.out.print("\nBefore sorting, the array is: [");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]\n");

        BubbleSort.sort(arr);

        System.out.print("After sorting, the array is: [");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]\n");
        
    }

}