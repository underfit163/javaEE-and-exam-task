import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Client {
    public static void main(String [] args) {
        if(args.length != 1) {
            System.out.println("Параметр командной строки не задан");
            return;
        }
        Book [] books = new Book[Integer.parseInt(args[0])];
        for(int i = 0; i< Integer.parseInt(args[0]); i++) {
            books[i] = new Book("name"+i,  Math.random()*100);
        }
        Arrays.stream(books).forEach(x-> System.out.println(x.getPay()));
        Socket socket;
        ObjectOutputStream oos;
        DataInputStream dis;
        try {
         socket = new Socket("localhost",1025);
         oos = new ObjectOutputStream(socket.getOutputStream());
         oos.writeObject(books);
         oos.flush();
         dis = new DataInputStream(socket.getInputStream());
         System.out.println("---------------------------");
         System.out.println(dis.readDouble());
         oos.close();
         dis.close();
         socket.close();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
}
