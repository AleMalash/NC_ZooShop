import Entity.Animal;
import Entity.Buyer;
import Entity.Queue;
import Entity.Shop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

/**
 * Created by Alexander on 14.12.2015.
 */
public class Main {

    public static int X = 0;
    public static int N = 0;

    public static void main(String[] args) {
        System.out.println("hello,zoo");

        InputStream input = null;
        Properties properties = new Properties();

        try{
            input = new FileInputStream("C:\\Users\\Alexander\\IdeaProjects\\Zoo\\src\\main\\resources\\additions.properties");
            properties.load(input);

            N = Integer.parseInt(properties.getProperty("N"));
            X = Integer.parseInt(properties.getProperty("X"));
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            if (input != null){
                try{
                    input.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        Animal dog = new Animal("0", "Alex", "", "10000", "Dog");
        dog.start();
        dog.generate();
        Animal cat = new Animal("1", "Steve", "", "5000", "Cat");
        cat.start();
        cat.generate();
        //Animal chicken = new Animal("2", "Poopey", "", "2500", "Chicken");
        //Animal mole = new Animal("3", "Semen", "","15000", "Mole");
        //Animal snack = new Animal("4", "Kaa", "", "12500", "Snack");
        //Animal hamster = new Animal("5", "Boris", "", "3000", "Hamster");

        List<Animal> container = new ArrayList<Animal>();

        Shop m = new Shop(X, container);

        dog.addElement(m);
        cat.addElement(m);

        List<Animal> emptyList = new ArrayList<Animal>();
        List<Buyer> b = new ArrayList<Buyer>();

        Buyer alexander = new Buyer("Alexander", emptyList);
        Buyer zhenya = new Buyer("Zhenya", emptyList);
        //Buyer dima = new Buyer("Dima", emptyList);

        b.add(alexander);
        b.add(zhenya);
        //b.add(dima);

        for (Buyer buyer: b){
            buyer.start();
        }
        Queue buyers = new Queue(b, N);
        buyers.start();
        while (true){
            for (Buyer buyer: b){
                buyer.buyAnimal(m);
            }
        }



//        zhenya.buyAnimal(m);
//        dima.buyAnimal(m);

//        Buyer kate = new Buyer("Kate", emptyList);
//
//        b.add(alexander);
//        b.add(zhenya);
//        b.add(dima);
//        b.add(kate);
//
//        Queue buyers = new Queue(b, N);
//
//        for (Buyer buyer : buyers.getBuyers()){
//            buyer.start();
//        }
//        buyers.addBuyer(new Buyer("Joe", emptyList));
//
//        for (Buyer buyer : buyers.getBuyers()){
//            m.buyAnimal(buyer);
//        }
//        Animal chicken = new Animal("2", "Poopey", "", "2500", "Chicken");
//        chicken.start();
//
//        m.addElement(chicken);
    }
}
