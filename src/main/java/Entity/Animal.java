package Entity;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by Alexander on 14.12.2015.
 */
public class Animal extends Thread{
    public Animal(String serialId, String name, String owner, String handling_time, String type) {
        this.serialId = serialId;
        this.animal = name;
        this.owner = owner;
        this.handling_time = handling_time;
        this.type = type;
    }

    public synchronized void generate(){
        createXML();
        check();

    }
    private String serialId;
    private String animal;
    private String owner;
    private String handling_time;
    private String type;

    public void addElement(Shop shop){
        if (shop.getContent().size() >= shop.getSize()){
            System.out.println("Too many animals int he shop! Waiting new clients...");
            try{
                animal.wait();
            } catch (InterruptedException e){
                System.out.println("Incorrect data!");
            }
        } else {
            shop.getContent().add(this);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String name) {
        this.animal = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getHandling_time() {
        return handling_time;
    }

    public void setHandling_time(String handling_time) {
        this.handling_time = handling_time;
    }

    private void createXML(){
        Element animal = new Element("animal");
        Document myDocument = new Document(animal);

        animal.addContent(returnAnimal(this.animal, this.owner, this.handling_time, this.type));

        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter("C:\\Users\\Alexander\\IdeaProjects\\Zoo\\src\\main\\resources\\animal" + serialId + ".xml");
            outputter.output(myDocument, fw);
            fw.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private Element returnAnimal(String name, String owner, String handling_time, String type){
        Element animal = new Element("animal");
        animal.setAttribute("serialId", serialId);
        animal.setAttribute("animal", name);
        animal.setAttribute("owner", owner);
        animal.setAttribute("handling_time", handling_time);
        animal.setAttribute("type", type);
        return animal;
    }

    private void check(){
        try
        {
            File xmlFile = new File("C:\\Users\\Alexander\\IdeaProjects\\Zoo\\src\\main\\resources\\animal"+ serialId +".xml");
            File xsdFile = new File("classes.xsd");
            SAXBuilder builder = new SAXBuilder();
            builder.setFeature(
                    "http://apache.org/xml/features/validation/schema", true
            );
            builder.setProperty("http://apache.org/xml/properties/schema"
                    + "/external-noNamespaceSchemaLocation", xsdFile.toURL().toString());

            Document doc = builder.build(xmlFile);

            System.out.println("Successfully parsed and validated");
        }
        catch ( Exception cause ) {
            System.err.println(cause.toString());
        }
    }
}