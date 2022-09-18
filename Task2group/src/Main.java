import java.io.*;
public class Main {
    public static void main(String[]args) throws IOException
    {
        if(args.length!=2){System.out.println("Ошибка");}
        else {
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            try {
                ois = new ObjectInputStream(new FileInputStream(args[0]));
                Matrix matrix =(Matrix) ois.readObject();
                int[] mas =Matrix.task2(matrix);
                oos = new ObjectOutputStream(new FileOutputStream(args[1]));
                oos.writeObject(mas);
            }catch (FileNotFoundException e)
            {
                System.out.println("Ошибка файла");
            }
            catch (IOException e)
            {
                System.out.println("Ошибка записи/чтения");
            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            } finally {
                ois.close();
                oos.close();
            }
        }
    }
}
