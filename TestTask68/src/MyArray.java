import java.io.*;
import java.util.*;

public class MyArray implements Serializable {
    private int[] arr;

    public MyArray(int size) {
        arr = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt();
        }
    }

    public int getSize() {
        return arr.length;
    }

    public int getVal(int i) {
        return arr[i];
    }

    public void setVal(int i, int val) {
        arr[i] = val;
    }

    public static int test68(MyArray array, int val, int count) {
        int countPosl = 0;
        int k = 0;
        for (int i = 0; i < array.getSize(); i++) {
            if (array.getVal(i) == val) {
                k++;
                if (i == array.getSize() - 1 && k >= count) {
                    countPosl++;
                }
            } else {
                if (k >= count) {
                    countPosl++;
                }
                k = 0;
            }
        }
        return countPosl;
    }
}
