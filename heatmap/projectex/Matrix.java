package projectex;

public class Matrix {
    int[][] matrix = new int[30][30];
    int row = 0;
    int col = 0;

    public void put(String input) {
        if ("-1".equals(input)) {
            row = 0;
            return;
        }
        String[] strings = new String[30];
        strings = input.split(" ");
        for (int i = 0; i < 30; i++) {
            matrix[row][i] = Integer.parseInt(strings[i]);
        }
        row++;
        //if (row == 30) {
        //    row = 0;
        //}
    }

    public void print() {
        System.out.println("=================================================");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("=================================================");
    }

    public int[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
