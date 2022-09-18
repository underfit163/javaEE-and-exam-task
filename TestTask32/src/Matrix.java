import java.util.*;
import java.io.*;

public class Matrix implements Serializable {
    private int[][] matr;
    private int size;

    public Matrix(int n) {
        Random r = new Random();
        size = n;
        matr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matr[i][j] = r.nextInt();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setVal(int i, int j, int val) {
        matr[i][j] = val;
    }

    public int getVal(int i, int j) {
        return matr[i][j];
    }

    public static boolean getMagic(Matrix matrix) {
        int prog = 0;
        int plus = 0;
        int test = 0;
        int[] mas = new int[matrix.getSize() * matrix.getSize()];
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                mas[i * matrix.getSize() + j] = matrix.getVal(i, j);
                prog += ++plus;
                test += matrix.getVal(i, j);
            }
        }
        System.out.println(prog);
        System.out.println(test);
        if (prog != test) return false;

        int h = mas.length / 2;
        while (h >= 1) {
            for (int i = h; i < mas.length; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (mas[j] < mas[j - h]) {
                        int temp = mas[j];
                        mas[j] = mas[j - h];
                        mas[j - h] = temp;
                    } else break;
                }
            }
            h/=2;
        }
        plus = 0;
        for (var el : mas) {
            ++plus;
            if (el != plus) return false;
        }

        int sum;
        for (int i = 0; i < matrix.getSize(); i++) {
            sum = 0;
            for (int j = 0; j < matrix.getSize(); j++) {
                sum += matrix.getVal(i, j) - matrix.getVal(j, i);
            }
            if (sum != 0) return false;
        }
        sum = 0;
        for (int i = 0; i < matrix.getSize(); i++) {
            sum += matrix.getVal(i, i) - matrix.getVal(i, matrix.getSize() - 1 - i);
        }
        return sum == 0;
    }
}
