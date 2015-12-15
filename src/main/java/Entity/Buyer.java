package Entity;

import java.util.List;
import java.util.Random;

/**
 * Created by Alexander on 15.12.2015.
 */
public class Buyer extends Thread{
    public Buyer(String name, List<Animal> pets) {
        this.buyer = name;
        this.pets = pets;
    }
    private String buyer;
    private List<Animal> pets;

    public void buyAnimal(Shop shop){
        Random rand = new Random();
        if (shop.getContent().size() == 0){
            System.out.println("Empty shop! Waiting new animals...");
            synchronized (this){
                try{
                    shop.generateAnimal();
                    this.wait();
                } catch (InterruptedException e){
                    System.out.println("Incorrect data!");
                }
            }
            return;
        }
            int currentAnimal = rand.nextInt(shop.getContent().size());
            Animal animal = shop.getContent().get(currentAnimal);
            shop.getContent().remove(currentAnimal);
            try{
                Thread.sleep(Integer.parseInt(animal.getHandling_time()));
            } catch (InterruptedException e){
                System.out.println("Incorrect data!");
            }


            animal.setOwner(buyer);
            animal.generate();

            System.out.println(buyer + " bought " + animal.getType() + " with name " + animal.getAnimal());
        }


    public String getBuyer(){
        return buyer;
    }

    public void addPet(Animal animal){
        pets.add(animal);
    }
}
