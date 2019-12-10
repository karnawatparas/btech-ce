public class SelectionSort {

    private int arr[];

    public SelectionSort(int arr[]) {
        this.arr = arr;
        sort();
    }

    private void sort() {

        int min, position;

        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            position = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    position = j;
                }
            }
            if (position != i) {
                int temp = arr[position];
                arr[position] = arr[i];
                arr[i] = temp;
            }
        }

    }

    public static void main(String[] args) {
        int arr[] = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        System.out.print("Before sorting >> [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");

        new SelectionSort(arr);

        System.out.print("After sorting >> [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

}