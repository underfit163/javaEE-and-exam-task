import java.io.*;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Должен быть 1 параметр");
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                Synchronizer synchronizer = new Synchronizer(Integer.parseInt(br.readLine()));
                Thread threadW = new Thread(new WriteThread(synchronizer));
                Thread threadR = new Thread(new ReadThread(synchronizer));
                threadW.start();
                threadR.start();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода");
            }
        }
    }
}
