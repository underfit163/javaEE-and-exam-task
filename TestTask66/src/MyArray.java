import java.io.*;
import java.util.Arrays;


public class MyArray implements Serializable {
    private double[] arr;
    private int size;

    public MyArray(int size) {
        this.size = size;
        arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Math.random();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public static double[] test66v1(MyArray array, double newVal) {
        double[] newArr = Arrays.copyOf(array.getArr(), array.getSize());
        //OR
        //double[] newArr = array.getArr().clone();

        int h = newArr.length / 2;
        while (h >= 1) {
            for (int i = h; i < newArr.length; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (newArr[j] < newArr[j - h]) {
                        double temp = newArr[j];
                        newArr[j] = newArr[j - h];
                        newArr[j - h] = temp;
                    } else break;
                }
            }
            h /= 2;
        }
        //OR
        //Arrays.sort(newArr);
        //array.setArr(Arrays.stream(newArr).sorted().toArray());

        int minK = -1;
        int k = 1;
        double minVal = newArr[0];
        double rarVal = newArr[0];
        for (int i = 1; i < newArr.length; i++) {
            if (minK == -1) {
                if (newArr[i] == rarVal) {
                    k++;
                } else {
                    minK = k;

                    k = 1;
                    rarVal = newArr[i];
                }
            } else {
                if (newArr[i] == rarVal) {
                    k++;
                } else {
                    if (k < minK) {
                        minK = k;
                        minVal = rarVal;
                    }
                    k = 1;
                    rarVal = newArr[i];
                }
            }
            if (i == newArr.length - 1 && k < minK) {
                minK = k;
                minVal = rarVal;
            }
        }
        for (int i = 0; i < array.getSize(); i++) {
            if (array.getVal(i) == minVal) {
                array.setVal(i, newVal);
            }
        }
        //OR
        /*double finalMinVal = minVal;
        array.setArr(Arrays.stream(array.getArr()).map(x -> {
            if (x == finalMinVal) x = newVal;
            return x;
        }).toArray());*/
        return array.getArr();
    }

    public static double[] test66v2(MyArray array, double newVal) {
        double[] mas = Arrays.copyOf(array.getArr(), array.getSize());
        double rarVal = 0;
        int rarK = 0;
        for (int i = 0; i < mas.length-1; i++) {
            double kVal = mas[i];
            int k = 1;
            for (int j = i+1; j < mas.length; j++) {
                if (mas[j] == kVal) {
                    k++;
                }
            }
            if (i == 0) {
                rarVal = kVal;
                rarK = k;
            } else if(k < rarK){
                rarVal = kVal;
                rarK = k;
            }
        }
        for (int i = 0; i < mas.length; i++) {
            if(rarVal == mas[i]) {
                mas[i] = newVal;
            }
        }
        return mas;
    }
}
