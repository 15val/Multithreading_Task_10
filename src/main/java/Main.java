import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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

        Market market = new Market(new AtomicInteger(100));

        ReentrantLock locker = new ReentrantLock();
        Broker firstBroker = new Broker(1, firstCompanyList, market, locker);
        Broker secondBroker = new Broker(2, secondCompanyList, market, locker);
        Broker thirdBroker = new Broker(3, thirdCompanyList, market, locker);

        List<Broker> brokers = new ArrayList<>();
        brokers.add(firstBroker);
        brokers.add(secondBroker);
        brokers.add(thirdBroker);

        ListIterator<Broker> iterator = brokers.listIterator();
        Broker current;
        while (iterator.hasNext()){
            current = iterator.next();
            current.start();
        }

        System.out.println("Finish!");

    }
}
