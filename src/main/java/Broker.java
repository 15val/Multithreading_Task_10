import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Broker extends Thread{
    private int brokerId;
    private int actionAmount;
    private Market market;
    private Action actionType;
    private Lock locker;

    public Broker(int brokerId, Action actionType, int actionAmount, Market market, ReentrantLock locker){
        this.brokerId = brokerId;
        this.actionAmount = actionAmount;
        this.actionType = actionType;
        this.market = market;
        this.locker = locker;
    }

    public void run(){
        Random random = new Random();
        for(int i = 0 ; i < actionAmount; i++){
                try {
                    locker.lock();
                    Thread.sleep(50);
                    if((Objects.equals(market.getIsMarketingPossible().get(), true))) {
                    if (random.nextBoolean() == true) { //action is sold
                        actionType.setPrice(new AtomicInteger(actionType.getPrice().intValue() + 10));
                        market.setIndex(new AtomicInteger(market.getIndex().intValue() + 1));
                        System.out.println("Broker " + this.brokerId + " sold action of company " + actionType.getCompanyName() + ". Current action price is: " + actionType.getPrice() + ". Current market index is: " + market.getIndex());
                    } else { //action is not sold
                        actionType.setPrice(new AtomicInteger(actionType.getPrice().intValue() - 10));
                        market.setIndex(new AtomicInteger(market.getIndex().intValue() - 1));
                        System.out.println("Broker " + this.brokerId + " didn't sell action of company " + actionType.getCompanyName() + ". Current action price is: " + actionType.getPrice() + ". Current market index is: " + market.getIndex());
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
