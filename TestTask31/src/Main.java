import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("2 file!");
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                int row = Integer.parseInt(br.readLine());
                int col = Integer.parseInt(br.readLine());
                Matrix matrix = new Matrix(row, col);
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        matrix.setVal(i, j, Double.parseDouble(br.readLine()));
                    }
                }
                Matrix.test31(matrix);
                //Matrix.test33v1(matrix);
                //Matrix.test33v2(matrix);
                PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
                pw.println(matrix.getRow());
                pw.println(matrix.getCol());
                for (int i = 0; i < matrix.getRow(); i++) {
                    for (int j = 0; j < matrix.getCol(); j++) {
                        pw.println(matrix.getVal(i,j));
                        System.out.print("  " + matrix.getVal(i,j));
                    }
                    System.out.println();
                }

                pw.flush();
                br.close();
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
