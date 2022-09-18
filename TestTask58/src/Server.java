import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
        ServerSocket serverSocket = new ServerSocket(1025);
        while (true) {
        Socket socket = serverSocket.accept();
        //new Thread(new ServerThread(socket)).start();
        //}
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        double mid = Calculator.getMid((Book[]) ois.readObject());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeDouble(mid);
        dos.flush();
        /*ois.close();
        dos.close();
        socket.close();
        serverSocket.close();*/
        }
        } catch (IOException e) {
            System.out.println("Exception1!");
        } catch (NegativePayException e) {
            System.out.println("Exception2!");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception3!");
        }
    }
}
