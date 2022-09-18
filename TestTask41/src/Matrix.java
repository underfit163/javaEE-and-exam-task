import java.io.*;
import java.util.*;

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

    public int getCol() {
        return col;
    }

    public double getVal(int i, int j) {
        return matr[i][j];
    }

    public void setVal(int i, int j, double val) {
        matr[i][j] = val;
    }

    public static int[] test41(Matrix matrix, double val, int count) {
        int n = 0;
        int[] mas = new int[n];
        int pos = -1;
        int k = 0;
        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < matrix.getCol(); j++) {
                if(matrix.getVal(i,j) == val) {
                    pos = i;
                    k++;
                } else {
                    if(k>=count) {
                      mas = Arrays.copyOf(mas,++n);
                      mas[n-1] = pos;
                    }
                    pos = -1;
                    k = 0;
                }
            }
            if(k>=count) {
                mas = Arrays.copyOf(mas,++n);
                mas[n-1] = pos;
            }
            pos = -1;
            k = 0;
        }
        return mas;
    }
}
