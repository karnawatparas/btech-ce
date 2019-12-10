public class QuickSortThreaded {
    private int[] arr;

    public QuickSortThreaded(int[] array) {
        arr = array;
        new Sorter(arr, 0, arr.length - 1).sort(0, arr.length - 1);
    }

    public static void main(String[] args) {

        int arr[] = { 123, 99, -10, 23, 8, 25, 0, 23, 999, 100 };

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        new QuickSortThreaded(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

class Sorter extends Thread {

    private final int[] a;
    private final int l;
    private final int h;

    public Sorter(int[] arr, int low, int high) {
        a = arr;
        l = low;
        h = high;
    }

    void sort(int f, int l) {
        if (f < l) {
            int mid = partition(f, l);
            Thread s1 = new Sorter(a, f, mid - 1);
            s1.start();
            Thread s2 = new Sorter(a, mid + 1, l);
            s2.start();
            try {
                s1.join();
                s2.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int partition(int f, int l) {
        int pivot = a[l];
        int j = f - 1;
        for (int i = f; i < l; i++) {
            if (pivot >= a[i]) {
                j++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[j + 1];
        a[j + 1] = a[l];
        a[l] = temp;

        return j + 1;
    }

    @Override
    public void run() {
        sort(l, h);
    }

}