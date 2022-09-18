public class Matrix {
    private int[][] matrix = null;
    private int rows;
    private int cols;
    public int[][] getMatrix() {
        return matrix;
    }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Matrix(int Rows, int Cols) {
        cols = Cols;
        rows = Rows;
        matrix = new int[Rows][Cols];
    }
    public int getValue(int Row, int Col) {
        if(Row > rows || Col > cols ||
                Col < 0 || Row < 0) return 0;
        else return matrix[Row][Col];
    }
    public void changeValue(int Row, int Col, int Val) {
        if (Row <= rows && Col <= cols)
            matrix[Row][Col] = Val;
    }

    /*public  static int task54(Matrix matr)
    {
        int k=0;
        int count =0;
        int [] mas = new int[matr.getCols()];
        int maxcount = 0;
        for(int i=0; i< matr.getRows();i++)
        {
            for(int j=0; j< matr.getCols(); j++)
            {
                if(matr.getValue(i,j)==1)
                {
                    while ((j<matr.getCols()&&(matr.getValue(i,j)==1)))
                    {
                        k++;
                        j++;
                    }
                    j--;
                }
                mas[j]=k;
                k=0;
            }
            for(int d=0; d<mas.length-1;d++)
            { if(mas[d]!=0)
              {count=1;}
                for (int t=d+1;t<mas.length;t++)
                {
                    if ((mas[t]!=0)&& mas[d]==mas[t])
                    {
                        count++;
                    }
                }
                if(count>maxcount)
                {
                    maxcount=count;
                    count=0;
                }
                mas[d]=0;
            }
            mas[mas.length-1]=0;
        }
        return maxcount;
    }*/

   /* public static int[][] task4(Matrix mat) {
        if((mat.getRows()%2!=0) || (mat.getCols()%2!=0)){int [][]nMat1 = new int[0][0]; return nMat1;}
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
    }*/

   /* public static int[] task2(Matrix mat)
    {

        int cm = 0;
        int [] mas = new int[mat.getRows()*mat.getCols()];
        int a=0, b=1, c=2;
        while (cm<(mas.length)) {
            for (int j = a; cm < mas.length && j < mat.getCols() - a; j++) {
                mas[cm] = mat.getValue(a, j);
                cm++;
            }
            for (int j = b; cm < mas.length && j < mat.getRows() - a; j++) {
                mas[cm] = mat.getValue(j, mat.getCols() - b);
                cm++;
            }
            for (int j = mat.getCols() - c; cm < mas.length && j >= a; j--) {
                mas[cm] = mat.getValue(mat.getRows() - b, j);
                cm++;
            }
            for (int j = mat.getRows() - c; cm < mas.length && j > a; j--) {
                mas[cm] = mat.getValue(j, a);
                cm++;
            }
            a++;
            b++;
            c++;
        }
        return mas;
    }*/

    public static int task11(Matrix mat)
    {   int count = 0;
        int k = 0;
        int max = 0;
        for (int i=0; i<mat.getRows(); i++)
        {
            for(int j=0; j<mat.getCols(); j++)
            {
                if(mat.getValue(i,j) == 1)
                {
                    k++;
                }
                else if(mat.getValue(i,j)==0 && k!=0) {
                    if (k > max) {
                        max = k;
                        k = 0;
                        count = 1;
                    }
                    else if(k == max)
                    {
                        count++;
                    }
                    k = 0;
                }
                if(j==mat.getCols()-1 && i==mat.getRows()-1 && k!=0){
                if(k > max)
                {
                    max = k;
                    count=1;
                }
                else if(k==max)
                {
                    count++;
                }
            }
        }
        }
        return count;
    }

    public static void Print(Matrix matrix){
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                System.out.print(matrix.getValue(i,j) + "\t");
            }
            System.out.println();
        }
        System.out.println("\t");
    }

}
