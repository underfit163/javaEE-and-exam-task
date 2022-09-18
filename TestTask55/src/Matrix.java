import java.io.*;
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

    public static double test55(Matrix matrix) {
        if(matrix.getRow() < 2 || matrix.getCol() < 2) {
            return 0;
        }
        double pr;
        double memPr = 0;
        for (int i = 0; i < matrix.getRow() - 1; i++) {
            for (int j = 0; j < matrix.getCol() - 1; j++) {
                pr = matrix.getVal(i, j) * matrix.getVal(i, j + 1) *
                        matrix.getVal(i + 1, j) * matrix.getVal(i + 1, j + 1);
                if(pr > memPr) {
                    memPr = pr;
                }
            }
        }
        return memPr;
    }
}
