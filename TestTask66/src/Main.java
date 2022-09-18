import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Ошибка! Нужно 2 файла");
        } else {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(args[0]));
                 DataOutputStream dos = new DataOutputStream(new FileOutputStream(args[1]))) {
                /*DataOutputStream dos = new DataOutputStream(new FileOutputStream(args[0]));
                dos.writeDouble(99);
                dos.writeInt(10);
                dos.writeDouble(3);
                dos.writeDouble(3);
                dos.writeDouble(3);
                dos.writeDouble(4);
                for (int i = 0; i < 5; i++) {
                    dos.writeDouble(5);
                }
                dos.writeDouble(6);
                dos.flush();*/

                double newVal = dis.readDouble();
                int size = dis.readInt();
                MyArray array = new MyArray(size);
                for (int i = 0; i < array.getSize(); i++) {
                    array.setVal(i, dis.readDouble());
                    System.out.println(array.getVal(i));
                }
                double[] d = MyArray.test66v1(array, newVal);
                System.out.println("Новый массив:");

                for (double v : d) {
                    dos.writeDouble(v);
                    System.out.println(v);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Ошибка чтения записи");
            }
        }
    }
}
