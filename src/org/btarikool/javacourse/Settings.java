package org.btarikool.javacourse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Settings {
    private List<String> usedNames = new ArrayList<>();
    private static Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("conf/kingdom.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Properties not found");
        }
    }

    private int chekIntValue(String propertyName){
        try{
           return Integer.parseInt(properties.getProperty(propertyName));
        } catch (Exception e){
            System.out.println("Invalid properties value" + propertyName);
            return 0;
        }
    }

    public String kingdomName() {
        try {
            return properties.getProperty("kingdomName");
        }catch (Exception e){
            System.out.println("Invalid properties value for kingdomName");
            return "";
        }
    }

    public String randomName() {
        try {
            List<String> listOfNames = Arrays.asList(properties.getProperty("randomNames").split(","));
            int rand = new Random().nextInt(listOfNames.size());
            String name = listOfNames.get(rand);
            usedNames.add(name);
            return name;
        }catch (Exception e){
            System.out.println("Invalid properties value for randomName");
            return "";
        }
    }

    public int qtyOfWizards() {
        return chekIntValue("wizardQuantity");
    }

    public int qtyOfLords() {
        return chekIntValue("lordQuantity");
    }

    public int qtyOfKnights() {
        return chekIntValue("knightQuantity");
    }

    public int gameRounds() {
        return chekIntValue("gameRounds");
    }

    public int qtyOfHeals() {
        return chekIntValue("qtyOfHeals");
    }

    public double getHealthNumber(Hooman h) {
        double min;
        double max;
        Random r = new Random();
        switch (h.getTitle()) {
            case "King":
                min = Double.parseDouble(properties.getProperty("kingMinHealth"));
                max = Double.parseDouble(properties.getProperty("kingMaxHealth"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
                //return (Math.random() * ((max - min) + 1)) + min;
            case "Lord":
                min = Double.parseDouble(properties.getProperty("lordMinHealth"));
                max = Double.parseDouble(properties.getProperty("lordMaxHealth"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            case "Wizard":
                min = Double.parseDouble(properties.getProperty("wizardMinHealth"));
                max = Double.parseDouble(properties.getProperty("wizardMaxHealth"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            case "Knight":
                min = Double.parseDouble(properties.getProperty("knightMinHealth"));
                max = Double.parseDouble(properties.getProperty("knightMaxHealth"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            default:
                min = Double.parseDouble(properties.getProperty("peasantMinHealth"));
                max = Double.parseDouble(properties.getProperty("peasantMaxHealth"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
        }
    }

    public double getPowerNumber(Hooman h) {
        double min;
        double max;
        Random r = new Random();
        switch (h.getTitle()) {
            case "King":
                min = Double.parseDouble(properties.getProperty("kingMinPower"));
                max = Double.parseDouble(properties.getProperty("kingMaxPower"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            case "Lord":
                min = Double.parseDouble(properties.getProperty("lordMinPower"));
                max = Double.parseDouble(properties.getProperty("lordMaxPower"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            case "Wizard":
                min = Double.parseDouble(properties.getProperty("wizardMinPower"));
                max = Double.parseDouble(properties.getProperty("wizardMaxPower"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            case "Knight":
                min = Double.parseDouble(properties.getProperty("knightMinPower"));
                max = Double.parseDouble(properties.getProperty("knightMaxPower"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
            default:
                min = Double.parseDouble(properties.getProperty("peasantMinPower"));
                max = Double.parseDouble(properties.getProperty("peasantMaxPower"));
                return r.doubles(min,(max+1)).findFirst().getAsDouble();
        }
    }
}