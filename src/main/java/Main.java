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
            input = new FileInputStream("C:\\Users\\Alexander\\IdeaProjects\\Zoo\\src\\main\\resources\\properties\\additions.properties");
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
        Animal chicken = new Animal("2", "Poopey", "", "2500", "Chicken");
        chicken.start();
        chicken.generate();
        Animal mole = new Animal("3", "Semen", "","15000", "Mole");
        mole.start();
        mole.generate();
        Animal snack = new Animal("4", "Kaa", "", "12500", "Snack");
        snack.start();
        snack.generate();
        Animal hamster = new Animal("5", "Boris", "", "3000", "Hamster");
        hamster.start();
        hamster.generate();

        List<Animal> container = new ArrayList<Animal>();

        Shop m = new Shop(X, container);

        dog.addElement(m);
        cat.addElement(m);
        chicken.addElement(m);
        mole.addElement(m);
        snack.addElement(m);
        hamster.addElement(m);

        List<Animal> emptyList = new ArrayList<Animal>();
        List<Buyer> b = new ArrayList<Buyer>();

        Buyer alexander = new Buyer("Alexander", emptyList);
        Buyer zhenya = new Buyer("Zhenya", emptyList);
        Buyer dima = new Buyer("Dima", emptyList);

        b.add(alexander);
        b.add(zhenya);
        b.add(dima);

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
    }
}
