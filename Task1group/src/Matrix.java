import java.io.*;

public class Matrix implements Serializable {
    private int rows;
    private int cols;
    private int[][] matrix;

    public Matrix(int Rows, int Cols) {
        rows = Rows;
        cols = Cols;
        matrix = new int[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getValue(int Row, int Col) {
        return matrix[Row][Col];
    }

    public void setValue(int Row, int Col, int Value) {
        matrix[Row][Col] = Value;
    }

    public static int task1(Matrix mat) {
        int count = 0;
        int k = 0;
        int max = 0;
        for (int i = 0; i < mat.getRows(); i++) {
            for (int j = 0; j < mat.getCols(); j++) {
                if (mat.getValue(i, j) == 1) {
                    k++;
                } else if (mat.getValue(i, j) == 0 && k != 0) {
                    if (k > max) {
                        max = k;
                        k = 0;
                        count = 1;
                    } else if (k == max) {
                        count++;
                    }
                    k = 0;
                }
                if (j == mat.getCols() - 1 && i == mat.getRows() - 1 && k != 0) {
                    if (k > max) {
                        max = k;
                        count = 1;
                    } else if (k == max) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

