import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1026);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Matrix matrix = new Matrix(3);
            matrix.setVal(0,0,6);
            matrix.setVal(0,1,1);
            matrix.setVal(0,2,8);
            matrix.setVal(1,0,7);
            matrix.setVal(1,1,5);
            matrix.setVal(1,2,3);
            matrix.setVal(2,0,2);
            matrix.setVal(2,1,9);
            matrix.setVal(2,2,4);
            /*matrix.setVal(0,0,5);
            matrix.setVal(0,1,5);
            matrix.setVal(0,2,5);
            matrix.setVal(1,0,5);
            matrix.setVal(1,1,5);
            matrix.setVal(1,2,5);
            matrix.setVal(2,0,5);
            matrix.setVal(2,1,5);
            matrix.setVal(2,2,5);*/

            oos.writeObject(matrix);
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            boolean b = ois.readBoolean();
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
