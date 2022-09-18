import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random R = new Random();
        int max = 1;
        int min = 0;
        Matrix matrix = new Matrix(4, 5);
        for (int i = 0; i < matrix.getRows(); i++)
            for (int j = 0; j < matrix.getCols(); j++)
                matrix.changeValue(i, j, R.nextInt(((max - min) + 1) + min));
        Matrix.Print(matrix);
        System.out.println();
        int f = Matrix.task11(matrix);
       // for (int i = 0; i < newMat.length; i++) {
                System.out.print(f+ "\t");

           // System.out.println();
      //  }
    }
}
