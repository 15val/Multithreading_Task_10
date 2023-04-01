import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Broker extends Thread{
    private int brokerId;
    private Market market;
    private List<Action> actions = new ArrayList<>();
    private Lock locker;
    public Broker(int brokerId, List<Action> actions, Market market, ReentrantLock locker){
        this.brokerId = brokerId;
        this.actions.addAll(actions);
        this.market = market;
        this.locker = locker;
    }

    public void run(){
        Random random = new Random();
        ListIterator<Action> iterator = actions.listIterator();
        Action current;
        while (iterator.hasNext()){

                try {
                    locker.lock();
                    Thread.sleep(50);
                    if((Objects.equals(market.getIsMarketingPossible().get(), true))) {
                    current = iterator.next();
                    if (random.nextBoolean() == true) { //action is sold
                        current.setPrice(new AtomicInteger(current.getPrice().intValue() + 10));
                        market.setIndex(new AtomicInteger(market.getIndex().intValue() + 1));
                        System.out.println("Broker " + this.brokerId + " sold action of company " + current.getCompanyName() + ". Current action price is: " + current.getPrice() + ". Current market index is: " + market.getIndex());
                    } else { //action is not sold
                        current.setPrice(new AtomicInteger(current.getPrice().intValue() - 10));
                        market.setIndex(new AtomicInteger(market.getIndex().intValue() - 1));
                        System.out.println("Broker " + this.brokerId + " didn't sell action of company " + current.getCompanyName() + ". Current action price is: " + current.getPrice() + ". Current market index is: " + market.getIndex());
                        if (market.checkDramaticIndexDecrease() == true) {
                            market.setIsMarketingPossible(new AtomicBoolean(false));
                            throw new InterruptedException("Marketing has been stopped due to dramatic index decrease");
                        }
                    }
                    }

                }
                catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                finally {
                   locker.unlock();
                }
        }

    }

}
