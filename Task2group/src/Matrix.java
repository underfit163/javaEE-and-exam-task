import java.io.*;
import java.util.Random;

public class Matrix implements Serializable {
    private int rows;
    private int cols;
    private int[][] matrix;
    private Random  r= new Random();
    public Matrix(int Rows, int Cols)
    {
        rows = Rows;
        cols = Cols;
        matrix = new int[rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
            {
                matrix[i][j] = r.nextInt();
            }
    }
    public  int getRows()
    {
        return rows;
    }
    public int getCols()
    {
        return  cols;
    }
    public void setValue(int Row,int Col,int Val)
    {
        matrix[Row][Col]=Val;
    }
    public int getValue(int Row, int Col)
    {
       return matrix[Row][Col];
    }

    public static int[] task2(Matrix mat)
    {
        int cm = 0;
        int [] mas = new int[mat.getRows()*mat.getCols()];
        int a=0, b=1, c=2;
        while (cm<=(mat.getRows()*mat.getCols()))
        {
            for (int j = a; j < mat.getCols()-a; j++)
            {
                mas[cm]=mat.getValue(a, j);
                cm++;
            }
            for (int j = b; j < mat.getRows()-a; j++)
            {
               mas[cm]=mat.getValue(j, mat.getCols() - b);
               cm++;
            }
            for (int j = mat.getCols() - c; j >= a; j--)
            {
                mas[cm]=mat.getValue(mat.getRows() - b, j);
                cm++;
            }
            for (int j = mat.getRows() - c; j > a;j--)
            {
                mas[cm]= mat.getValue(j, a);
                cm++;
            }
            a++;
            b++;
            c++;
        }
        return mas;
    }
}
