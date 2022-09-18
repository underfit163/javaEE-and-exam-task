import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public static double getMid(Book[] books) throws NegativePayException {
        double mid = -1;
       /* //Улучшенный пузырек:
        boolean flagSorted = false;
        int end = 0;
        while (!flagSorted) {
            flagSorted = true;
            for (int i = 0; i < books.length - 1 - end; i++) {
                if (books[i].getPay() > books[i + 1].getPay()) {
                    double temp = books[i].getPay();
                    books[i].setPay(books[i + 1].getPay());
                    books[i + 1].setPay(temp);
                    flagSorted = false;
                }
            }
            end++;
        }*/

        //Сортировка вставками:
        /*for (int i = 1; i < books.length; i++) {
            int k = i;
            while (k >= 1 && books[k].getPay() < books[k - 1].getPay()) {
                double temp = books[k].getPay();
                books[k].setPay(books[k - 1].getPay());
                books[k - 1].setPay(temp);
                k--;
            }
        }*/
        //ИЛИ
        /*for (int i = 1; i < books.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (books[j].getPay() < books[j - 1].getPay()) {
                    double temp = books[j].getPay();
                    books[j].setPay(books[j - 1].getPay());
                    books[j - 1].setPay(temp);
                } else break;
            }
        }*/

        //Сортировка Шелла:
        //шаг разбиения
        int h = books.length / 2;
        /*while (h >= 1) {
            for (int i = h; i < books.length; i++) {
                int k = i;
                while (k >= h && books[k].getPay() < books[k - h].getPay()) {
                    double temp = books[k].getPay();
                    books[k].setPay(books[k - h].getPay());
                    books[k - h].setPay(temp);
                    k -= h;
                }
            }
            h /= 2;
        }*/
        //ИЛИ
        while (h >= 1) {
            for (int i = h; i < books.length; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (books[j].getPay() < books[j - h].getPay()) {
                        double temp = books[j].getPay();
                        books[j].setPay(books[j - h].getPay());
                        books[j - h].setPay(temp);
                    } else break;
                }
            }
            h /= 2;
        }
        System.out.println("------------------------------------");
        Arrays.stream(books).forEach(x -> System.out.println(x.getPay()));
        if (books.length % 2 == 0) {
            mid = (books[books.length / 2 - 1].getPay() + books[books.length / 2].getPay()) / 2;
        } else {
            mid = books[books.length / 2].getPay();
        }
        return mid;
    }
}
