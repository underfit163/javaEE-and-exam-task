import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Введите 2 параметра");
        } else {
            try {
               /* DataOutputStream dos = new DataOutputStream(new FileOutputStream(args[0]));
                dos.writeInt(3);
                dos.writeInt(4);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        dos.writeDouble(i + j);
                    }
                }
                dos.flush();*/

                DataInputStream dis = new DataInputStream(new FileInputStream(args[0]));
                int row = dis.readInt();
                int col = dis.readInt();
                Matrix matrix = new Matrix(row, col);
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        matrix.setVal(i, j, dis.readDouble());
                        System.out.print("  "+ matrix.getVal(i,j));
                    }
                    System.out.println();
                }
                double pr = Matrix.test55(matrix);
                System.out.println("Произведение: " + pr);
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(args[1]));
                dos.writeDouble(pr);
                dos.flush();
                dis.close();
                dos.close();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода");
            }
        }
    }
}
