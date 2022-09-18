import java.io.*;
import java.util.*;

public class MyArray implements Serializable {
    private double[] arr;

    public MyArray(int size) {
        arr = new double[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextDouble();
        }
    }

    public int getSize() {
        return arr.length;
    }

    public double getVal(int i) {
        return arr[i];
    }

    public void setVal(int i, double val) {
        arr[i] = val;
    }

    public double[] getArr() {
        return arr;
    }

    public void setArr(double[] arr) {
        this.arr = arr;
    }

    public static double[] test67(MyArray array) {
        double[] mas = Arrays.copyOf(array.getArr(), array.getSize());
        for (int i = 0; i < mas.length - 1; i++) {
            for (int j = i+1; j < mas.length; j++) {
                if (mas[i] == mas[j]) {
                    System.arraycopy(mas, j + 1, mas, j, mas.length - (j + 1));
                    mas = Arrays.copyOf(mas, mas.length - 1);
                    j--;
                }
            }
        }
        return mas;
    }

    public static double[] removeDuplicates(MyArray myArray) {
        if (myArray != null) {
            if (myArray.getSize() == 0 || myArray.getSize() == 1) {
                return myArray.getArr();
            }

            Set<Double> set = new HashSet<>();
            for (int i = 0; i < myArray.getSize(); i++) {
                set.add(myArray.getArr()[i]);
            }

            double[] answer = new double[set.size()];
            Iterator<Double> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                answer[i] = (double) it.next();
                i++;
            }
            return answer;
        }
        return null;
    }
}
