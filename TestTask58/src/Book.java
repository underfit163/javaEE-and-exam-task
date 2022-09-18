import java.io.*;

public class Book implements Serializable {
    private String name;
    private double pay;

    public Book(String name, double pay) {
        this.name = name;
        this. pay = pay;
    }
    public String getName() {
        return name;
    }
    public double getPay() {
        return pay;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPay(double pay) throws NegativePayException  {
        if(pay < 0) throw new NegativePayException(pay);
        this.pay = pay;
    }

}
