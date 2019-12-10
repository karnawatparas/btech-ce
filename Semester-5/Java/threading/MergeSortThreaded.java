public class MergeSortThreaded {

    private int arr[];

    public MergeSortThreaded(int a[], int left, int right) {
        this.arr = a;
        new MergeSort(arr, left, right).sort(left, right);
    }

    public static void main(String[] args) {
        int array[] = { 999, 99, 9, 1, 11, 111, -3, -33, -333, 33, 3 };

        System.out.print("Before sorting >> ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();

        new MergeSortThreaded(array, 0, array.length - 1);

        System.out.print("After sorting >> ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

}

class MergeSort extends Thread {

    private int arr[];
    private int left;
    private int right;

    public MergeSort(int arr[], int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    private void merge(int left, int mid, int right) {

        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int leftArr[] = new int[leftSize];
        int rightArr[] = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[i + left];
        }

        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = arr[mid + i + 1];
        }

        int k = left;
        int i = 0;
        int j = 0;

        while (i < leftSize && j < rightSize) {
            if (leftArr[i] < rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }

    }

    public void sort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            MergeSort m1 = new MergeSort(arr, left, mid);
            MergeSort m2 = new MergeSort(arr, mid + 1, right);
            m1.start();
            m2.start();
            try {
                m1.join();
                m2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            merge(left, mid, right);
        }
    }

    @Override
    public void run() {
        sort(left, right);
    }

}