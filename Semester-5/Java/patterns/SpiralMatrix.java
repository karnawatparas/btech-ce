public class SpiralMatrix {

    public static void printSpiral(int rows, int columns) {

        int matrix[][] = new int[rows][columns];

        int value = 1;

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = columns - 1;

        while (true) {

            if (left > right) {
                break;
            }

            for (int i = left; i <= right; i++) {
                matrix[top][i] = value++;
            }
            top++;

            if (top > bottom) {
                break;
            }

            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = value++;
            }
            right--;

            if (left > right) {
                break;
            }

            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = value++;
            }
            bottom--;

            if (top > bottom) {
                break;
            }

            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = value++;
            }
            left++;

        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        System.out.println();
        printSpiral(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println();
    }

}