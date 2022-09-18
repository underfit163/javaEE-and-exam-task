import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Matrix implements Serializable {
    private double[][] matr;
    private int row;
    private int col;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        matr = new double[row][col];
        Random r = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matr[i][j] = r.nextDouble();
            }
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getVal(int row, int col) {
        return matr[row][col];
    }

    public void setVal(int row, int col, double val) {
        matr[row][col] = val;
    }

    public static Matrix test31(Matrix matrix) {
        int h = matrix.getCol() / 2;
        while (h >= 1) {
            for (int j = h; j < matrix.getCol(); j++) {
                for (int i = j; i >= h; i = i - h) {
                    if (matrix.getVal(0, i) < matrix.getVal(0, i - h)) {
                        for (int k = 0; k < matrix.getRow(); k++) {
                            double temp = matrix.getVal(k, i);
                            matrix.setVal(k, i, matrix.getVal(k, i - h));
                            matrix.setVal(k, i - h, temp);
                        }
                    } else break;
                }
            }
            h /= 2;
        }
        return matrix;
    }

    public static Matrix test33v1(Matrix matrix) {
        int n = 0;
        int[] masIJ = new int[0];
        int masI = 0;
        int masJ = 1;
        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < matrix.getCol(); j++) {
                if (matrix.getVal(i, j) == 0) {
                    n += 2;
                    masIJ = Arrays.copyOf(masIJ, n);
                    masIJ[masI] = i;
                    masIJ[masJ] = j;
                    masI++;
                    masJ++;
                }
            }
        }
        for (int i = 0; i < masIJ.length; i++) {
            if (i % 2 == 0) {
                for (int k = 0; k < matrix.getCol(); k++) {
                    matrix.setVal(masIJ[i], k, 0);
                }
            } else {
                for (int k = 0; k < matrix.getRow(); k++) {
                    matrix.setVal(k, masIJ[i], 0);
                }
            }
        }
        return matrix;
    }

    public static Matrix test33v2(Matrix matrix) {
        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < matrix.getCol(); j++) {
                if (String.valueOf(i).contains("0") || String.valueOf(j).contains("0")) {
                    matrix.setVal(i,j,0);
                }
            }
        }
        return matrix;
    }
}
