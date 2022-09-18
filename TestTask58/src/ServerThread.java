import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            double mid = Calculator.getMid((Book[]) ois.readObject());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeDouble(mid);
            dos.flush();
            ois.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Exception1!");
        } catch (NegativePayException e) {
            System.out.println("Exception2!");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception3!");
        }

    }
}
