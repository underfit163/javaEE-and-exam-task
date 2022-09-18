import java.io.*;
import java.util.Random;

public class Matrix implements Serializable {
    private int rows;
    private int cols;
    private int[][] matrix;
    private Random r = new Random();

    public Matrix(int Rows, int Cols) {
        rows = Rows;
        cols = Cols;
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = r.nextInt();
            }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setValue(int Row, int Col, int Val) {
        matrix[Row][Col] = Val;
    }

    public int getValue(int Row, int Col) {
        return matrix[Row][Col];
    }

    public static int[][] task4(Matrix mat) {

        if(mat.getRows()%2!=0 || mat.getCols()%2!=0){int [][]nMat1 = new int[0][0]; return nMat1;}
        else{int [][]nMat = new int[mat.getRows()/2][mat.getCols()/2];
        for (int i=0;i<mat.getRows();i=i+2)
        {
            for (int j=0; j< mat.getCols();j=j+2)
            {
                nMat[i/2][j/2] = mat.getValue(i,j)*mat.getValue(i+1,j+1)-mat.getValue(i,j+1)*mat.getValue(i+1,j);
            }
        }
        return nMat;
    }
    }
}