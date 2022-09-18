public class Synchronizer {
    private double[] mas;
    private Object lock = new Object();
    private volatile int i = 0;
    private int n;
    private volatile boolean flag = false;

    public Synchronizer(int n) {
        this.n = n;
        mas = new double[n];
    }

    public void writeMas() {
        try {
            synchronized (lock) {
                while (flag) lock.wait();
                if (i<n) mas[i] = Math.random();
                else return;
                flag = true;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            System.out.println("Exception");
        }

    }

    public void readMas() {
        try {
            synchronized (lock) {
                while (!flag) lock.wait();
                System.out.println(mas[i++]);
                flag = false;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            System.out.println("Exception");
        }
    }

    public boolean canRW() {
        return i < n;
    }
}
