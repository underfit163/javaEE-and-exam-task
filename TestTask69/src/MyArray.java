import java.io.*;
import java.util.*;

public class MyArray implements Serializable {
    private double[] arr;

    public MyArray(int size) {
        arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Math.random();
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

    public static int test69(MyArray array) {
        int indexGoal = 0;
        int longK = 0;

        int k;
        int index;
        boolean positive;
        if (array.getVal(0) >= 0) {
            k = 1;
            index = 0;
            positive = true;
        } else {
            k = 1;
            index = 0;
            positive = false;
        }
        for (int i = 1; i < array.getSize(); i++) {
            if (array.getVal(i) >= 0) {
                if (positive) {
                    if (k > longK) {
                        indexGoal = index;
                        longK = k;
                    }
                    k = 1;
                    index = i;
                    positive = true;
                } else {
                    k++;
                    positive = true;
                }
            } else {
                if (positive) {
                    k++;
                    positive = false;
                } else {
                    if (k > longK) {
                        indexGoal = index;
                        longK = k;
                    }
                    k = 1;
                    index = i;
                    positive = false;
                }
            }
        }
        if (k > longK) {
            indexGoal = index;
        }
        return indexGoal;
    }

    public static double[] addValMas(MyArray array, int i, double val) {
        array.setArr(Arrays.copyOf(array.getArr(),array.getSize()+1));
        System.arraycopy(array.getArr(), i, array.getArr(), i+1, array.getSize()-1-i);
        array.setVal(i,val);
        return array.getArr();
    }
}
