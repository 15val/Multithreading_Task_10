import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        List<Action> firstCompanyList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            firstCompanyList.add(new Action("FIRST", new AtomicInteger(500)));
        }
        List<Action> secondCompanyList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            secondCompanyList.add(new Action("SECOND", new AtomicInteger(400)));
        }
        List<Action> thirdCompanyList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            thirdCompanyList.add(new Action("THIRD", new AtomicInteger(300)));
        }

        Broker firstBroker = new Broker(1, firstCompanyList);
        Broker secondBroker = new Broker(2, secondCompanyList);
        Broker thirdBroker = new Broker(3, thirdCompanyList);

        List<Broker> brokers = new ArrayList<>();
        brokers.add(firstBroker);
        brokers.add(secondBroker);
        brokers.add(thirdBroker);

        Market market = new Market(new AtomicInteger(100), brokers);
        market.start();
    }
}
