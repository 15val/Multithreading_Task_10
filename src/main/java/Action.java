import java.util.concurrent.atomic.AtomicInteger;

public class Action {
    private String companyName;
    private AtomicInteger price;
    public Action(String companyName, AtomicInteger price) {
        this.companyName = companyName;
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AtomicInteger getPrice() {
        return price;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPrice(AtomicInteger price) {
        this.price = price;
    }
}
