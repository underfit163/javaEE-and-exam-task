import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(1025);
             Socket socket = ss.accept();
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        ) {
            MyArray array = (MyArray) ois.readObject();
            /*StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
            st.nextToken();
            int val = (int) st.nval;
            System.out.println(val);
            st.nextToken();
            int c = (int) st.nval;
            System.out.println(c);*/

            int val = Integer.parseInt(br.readLine());
            System.out.println(val);
            int c = Integer.parseInt(br.readLine());
            System.out.println(c);
            int count = MyArray.test68(array, val, c);
            dos.writeInt(count);
            System.out.println(count);

        } catch (ClassNotFoundException e) {
            System.out.println("exception class");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
}
