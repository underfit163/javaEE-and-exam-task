public class WriteThread implements Runnable {
    private Synchronizer synchronizer;

    public WriteThread(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }
    @Override
    public void run() {
        while (synchronizer.canRW()){
            synchronizer.writeMas();
        }
    }
}
