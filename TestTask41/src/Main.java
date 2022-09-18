import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Ошибка!");
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                double val = Double.parseDouble(br.readLine());
                int count = Integer.parseInt(br.readLine());
                int row = Integer.parseInt(br.readLine());
                int col = Integer.parseInt(br.readLine());
                Matrix matrix = new Matrix(row, col);
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        matrix.setVal(i,j, Double.parseDouble(br.readLine()));
                    }
                }
                int[] mas = Matrix.test41(matrix,val,count);

                PrintWriter pr = new PrintWriter(new FileWriter(args[1]));
                for (int ma : mas) {
                    pr.println(ma);
                }
                br.close();
                pr.flush();
                pr.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Ошибка ввода вывода");
            }
        }
    }
}
