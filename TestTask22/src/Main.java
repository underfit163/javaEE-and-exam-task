import java.io.*;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Не 2 параметра");
        } else {
            try {
                /*DataOutputStream dos = new DataOutputStream(new FileOutputStream(args[0]));
                dos.writeInt(5);
                dos.writeDouble(43);
                dos.writeDouble(2.4);
                dos.writeDouble(54.2);
                dos.writeDouble(3.3);
                dos.writeDouble(2.4);*/

                DataInputStream dis = new DataInputStream(new FileInputStream(args[0]));
                int n = dis.readInt();
                LinkedListVector llv = new LinkedListVector(n);
                for (int i = 0; i < llv.getSize(); i++) {
                    System.out.println(llv.getValueByIndex(i));
                    llv.setValue(i, dis.readDouble());
                    System.out.println("--------------------" + llv.getValueByIndex(i));
                }
                llv.setValue(0, 100);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(args[1]));
                oos.writeObject(llv);

                /*ObjectInputStream ois = new ObjectInputStream(new FileInputStream(args[1]));
                LinkedListVector listVector = (LinkedListVector) ois.readObject();
                System.out.println("Проверка:");
                for (int i = 0; i < listVector.getSize(); i++) {
                    System.out.println(listVector.getValueByIndex(i));
                }*/
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка файла");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
    }
}
