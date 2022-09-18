import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1025);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MyArray array = new MyArray(13);
            array.setVal(0, 4);
            array.setVal(1, 5);
            array.setVal(2, 6);
            array.setVal(3, 6);
            array.setVal(4, 6);
            array.setVal(5, 6);
            array.setVal(6, 5);
            array.setVal(7, 6);
            array.setVal(8, 6);
            array.setVal(9, 6);
            array.setVal(10, 1);
            array.setVal(11, 6);
            array.setVal(12, 6);

            oos.writeObject(array);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
