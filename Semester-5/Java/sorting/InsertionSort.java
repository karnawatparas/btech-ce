import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class InsertionSort {

    public static void main(String[] args) throws IOException {
        int size;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the number of elements >> ");
        size = Integer.parseInt(br.readLine());
        int arr[] = new int[size];
        int index = -1;

        for (int i = 0; i < size; i++) {

            System.out.print("Enter the number >> ");
            int temp = Integer.parseInt(br.readLine());

            while (index != -1 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = temp;
            index = i;

            System.out.print("Array : [ ");
            for (int j = 0; j <= index; j++) {
                System.out.print(arr[j] + " ");
            }

            System.out.println("]");

        }

    }

}