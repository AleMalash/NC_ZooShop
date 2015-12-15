package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexander on 15.12.2015.
 */
public class Queue  extends Thread{
    public Queue(List<Buyer> buyers, int size) {
        this.buyers = buyers;
        this.size = size;
    }

    private List<Buyer> buyers;
    private int size;

    public List<Buyer> getBuyers(){
        return buyers;
    }

    public synchronized void addBuyer(Buyer buyer){
        if (buyers.size() >= size){
            try{
                buyer.wait();
            } catch (InterruptedException e){
                System.out.println("Queue is very large! Please wait...");
            }
        } else{
            buyers.add(buyer);
            buyer.start();
        }
    }
}
