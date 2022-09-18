import java.io.*;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("2 файла!");
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[0]));
                 PrintWriter pw = new PrintWriter(new FileWriter(args[1]))) {
                /*MyArray myArray1 = new MyArray(14);
                myArray1.setVal(0, -1);
                myArray1.setVal(1, -1);
                myArray1.setVal(2, -1);
                myArray1.setVal(3, -2);
                myArray1.setVal(4, 3);
                myArray1.setVal(5, -1);
                myArray1.setVal(6, 1);
                myArray1.setVal(7, 1);
                myArray1.setVal(8, 1);
                myArray1.setVal(9, -5);
                myArray1.setVal(10, 1);
                myArray1.setVal(11, -1);
                myArray1.setVal(12, 5);
                myArray1.setVal(13, -5);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(args[0]));
                oos.writeObject(myArray1);
                oos.flush();*/
                MyArray array = (MyArray) ois.readObject();
                int i = MyArray.test69(array);
                pw.println(i);
            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            } catch (FileNotFoundException e) {
                System.out.println("file not found!");
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода");
            }
        }
    }
}
