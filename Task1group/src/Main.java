import java.io.*;
public class Main {
    public  static void main(String[] args) throws IOException
    {
        if(args.length!=2) System.out.println("Ошибка");
        else {
            //BufferedReader br = null;
            PrintWriter pw = null;
            StreamTokenizer st = null;
            //BufferedWriter bw = null;
            try {
                //br = new BufferedReader(new FileReader(args[0]));
                st = new StreamTokenizer(new FileReader(args[0]));

                //int rows = Integer.parseInt(br.readLine());
                //int cols = Integer.parseInt(br.readLine());
                st.nextToken();
                int rows = (int) st.nval;
                st.nextToken();
                int cols = (int) st.nval;

                Matrix mat = new Matrix(rows,cols);
                for(int i=0; i<rows;i++)
                    for (int j=0; j<cols;j++)
                    {
                        //mat.setValue(i,j,Integer.parseInt(br.readLine()));
                        st.nextToken();
                        mat.setValue(i,j,(int) st.nval);
                    }
                pw = new PrintWriter(new FileWriter(args[1]));
                pw.println(Matrix.task1(mat));
                //bw = new BufferedWriter( new FileWriter(args[1]));
                //bw.write(Matrix.task1(mat));
            }catch (FileNotFoundException e)
            {
                System.out.println("Ошибка");
            }
            catch (IOException e)
            {
                System.out.println("Ошибка");
            }finally {
                //br.close();
                pw.close();
                //bw.close();
            }
        }
    }
}
