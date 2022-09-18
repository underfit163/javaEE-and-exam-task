public class ReadThread implements Runnable {
    private Synchronizer synchronizer;

    public ReadThread(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    public void run() {
        while (synchronizer.canRW()) {
            synchronizer.readMas();
        }
    }
}
