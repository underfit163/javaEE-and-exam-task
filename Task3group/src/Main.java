import java.io.*;
public class Main {
    public static void main(String []args) throws IOException
    {
        if(args.length!=2) System.out.println("Ошибка");
        else {
            DataInputStream dis= null;
            DataOutputStream dos = null;
            try {
                dis = new DataInputStream(new FileInputStream(args[0]));
                int Row = dis.readInt();
                int Col = dis.readInt();
                Matrix mat = new Matrix(Row,Col);
                for(int i= 0; i<Row; i++)
                {
                    for(int j =0; j<Col;j++)
                    {
                        mat.setValue(i,j,dis.readDouble());
                    }
                }
                dos=new DataOutputStream(new FileOutputStream(args[1]));
                dos.writeDouble(Matrix.task3(mat));

            }catch (FileNotFoundException e)
            {
                System.out.println("Ошибка1");
            }
            catch (IOException e)
            {
                System.out.println("Ошибка2");
            }finally {
                dis.close();
                dos.close();
            }
        }
    }
}
