import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[]args) throws IOException {
        if (args.length != 2) System.out.println("Ошибка");
        else {
            ServerSocket serverSocket = null;
            Socket clientSocket = null;
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            try {
                serverSocket = new ServerSocket(4444);
                clientSocket = serverSocket.accept();
                ois = new ObjectInputStream(clientSocket.getInputStream());
                Matrix matrix = (Matrix) ois.readObject();
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(Matrix.task4(matrix));

            }
            catch (FileNotFoundException e)
            {
                System.out.println("Файл не найден");
            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("Ошибка ввода/вывода");
            }finally {
                ois.close();
                oos.close();
                clientSocket.close();
                serverSocket.close();
            }
        }
    }
}
