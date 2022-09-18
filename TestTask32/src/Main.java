import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1026);
            Socket socket = ss.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Matrix matrix = (Matrix) ois.readObject();
            boolean b = Matrix.getMagic(matrix);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            oos.writeBoolean(b);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
