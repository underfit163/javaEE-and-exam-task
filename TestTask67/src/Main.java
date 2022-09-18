import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Должно быть 2 параметра!");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]));
                 PrintWriter pr = new PrintWriter(new FileWriter(args[1]))) {
                int size = Integer.parseInt(br.readLine());
                MyArray myArray = new MyArray(size);
                for (int i = 0; i < size; i++) {
                    myArray.setVal(i, Double.parseDouble(br.readLine()));
                }
                double[] arr = MyArray.test67(myArray);
                //double[] arr = MyArray.removeDuplicates(myArray);
                for (int i = 0; i < arr.length; i++) {
                    pr.println(arr[i]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл отсутствует!");
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода");
            }
        }
    }
}
