public class HeapSort {

    private int arr[];

    public HeapSort(int arr[]) {
        this.arr = arr;
        sort();
    }

    private void heapify(int arr[], int size, int index) {

        int largest = index;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != index) {
            int temp = arr[largest];
            arr[largest] = arr[index];
            arr[index] = temp;
            heapify(arr, size, largest);
        }

    }

    private void sort() {

        int size = arr.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr, size, i);
        }

        for (int i = size - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }

    }

    public static void main(String[] args) {
        int arr[] = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        System.out.print("Before sorting >> [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");

        new HeapSort(arr);

        System.out.print("After sorting >> [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

}