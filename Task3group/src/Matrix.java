import java.io.*;
import java.util.Random;

public class Matrix implements Serializable {
    private int rows;
    private int cols;
    private double[][] matrix;
    private Random r;

    public Matrix(int Rows, int Cols) {
        rows = Rows;
        cols = Cols;
        matrix = new double[rows][cols];
        for(int i= 0; i<Rows; i++)
        {
            for(int j =0; j<Cols;j++)
            {
                matrix[i][j]=r.nextDouble();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double getValue(int Row, int Col) {
        return matrix[Row][Col];
    }

    public void setValue(int Row, int Col, double Value) {
        matrix[Row][Col] = Value;
    }

    public static double task3(Matrix mat) {
        double max = mat.getValue(0,0)*mat.getValue(0,1)*
                mat.getValue(1,0)*mat.getValue(1,1);
        double sum;
        for (int i = 0; i < mat.getRows()-1 ; i++) {
            for (int j = 0; j < mat.getCols()-1; j++) {
                sum = mat.getValue(i,j)*mat.getValue(i+1,j)*
                        mat.getValue(i,j+1)*mat.getValue(i+1,j+1);
                if(sum > max) max = sum;
            }
        }
        return max;
    }
}