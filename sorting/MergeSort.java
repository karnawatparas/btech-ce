public class MergeSort {

    public static void main(String[] args) {

        int arr[] = { 90, -10, 100, 23, 26, 81, 1, -19, 999, 1000, 123, 123 };

        System.out.print("\nBefore sorting, the array is: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]\n");

        MergeSort.mergeSort(arr, arr.length);

        System.out.print("After sorting, the array is: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]\n");

    }

    public static void mergeSort(int arr[], int len) {

        if(len < 2) {
            return;
        }

        int mid = len/2;
        int left[] = new int[mid];
        int right[] = new int[len - mid];

        for(int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for(int i = mid; i < len; i++) {
            right[i - mid] = arr[i];
        }

        mergeSort(left, mid);
        mergeSort(right, len - mid);

        merge(arr, left, right, mid, len - mid);

    }

    private static void merge(int arr[], int left[], int right[], int lSize, int rSize) {

        int i, j, k;
        i = j = k = 0;

        while(i < lSize && j < rSize) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while(i < lSize) {
            arr[k++] = left[i++];
        }

        while(j < rSize) {
            arr[k++] = right[j++];
        }

    }

}