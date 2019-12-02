public class QuickSort {

    public static void main(String[] args) {

        int arr[] = { 90, -10, 100, 23, 26, 81, 1, -19, 999, 1000, 123, 123 };

        System.out.print("\nBefore sorting, the array is: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]\n");

        QuickSort.quickSort(arr, 0, arr.length - 1);

        System.out.print("After sorting, the array is: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]\n");

    }

    private static int partition(int arr[], int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for(int j = low; j < high; j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;

    }

    public static void quickSort(int arr[], int low, int high) {

        if(low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }

    }

}