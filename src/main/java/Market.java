import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Market extends Thread{
    private AtomicBoolean isMarketingPossible;
    private AtomicInteger defaultIndex;
    private AtomicInteger index;
    private int dramaticDecreaseDelta = 25;
    private List<Broker> brokers = new ArrayList<>();

    public Market(AtomicInteger index, List<Broker> brokers){
        this.defaultIndex = index;
        this.index = index;
        this.brokers.addAll(brokers);
        this.isMarketingPossible = new AtomicBoolean(true);
    }

    public AtomicBoolean getIsMarketingPossible() {
        return isMarketingPossible;
    }

    public void setIsMarketingPossible(AtomicBoolean isMarketingPossible) {
        this.isMarketingPossible = isMarketingPossible;
    }

    public AtomicInteger getDefaultIndex() {
        return defaultIndex;
    }

    public AtomicInteger getIndex() {
        return index;
    }

    public void setIndex(AtomicInteger index) {
        this.index = index;
    }

    public boolean checkDramaticIndexDecrease(){
        if(defaultIndex.intValue() - index.intValue() > dramaticDecreaseDelta){
            return true;
        }
        else return false;
    }


    public void run(){
        ListIterator<Broker> iterator = brokers.listIterator();
        Broker current;
        try{
            while (iterator.hasNext()){
                Thread.sleep(50);
                current = iterator.next();
                current.start();
            }
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

}
