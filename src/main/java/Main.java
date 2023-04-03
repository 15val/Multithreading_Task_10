import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Action first = new Action("FIRST", new AtomicInteger(500));
        Action second = new Action("SECOND", new AtomicInteger(400));
        Action third = new Action("THIRD", new AtomicInteger(300));


        Market market = new Market(new AtomicInteger(100));

        ReentrantLock locker = new ReentrantLock();
        Broker firstBroker = new Broker(1, first, 5, market, locker);
        Broker secondBroker = new Broker(2, second, 5, market, locker);
        Broker thirdBroker = new Broker(3, third, 5, market, locker);

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
