public class NegativePayException extends Exception {
    private double pay;
    public NegativePayException(double pay) {
        super("Не верно задана цена книги: " + pay);
        this.pay = pay;
    }
}
