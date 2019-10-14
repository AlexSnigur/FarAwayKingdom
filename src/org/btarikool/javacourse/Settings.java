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

    public String kingdomName() {
        return properties.getProperty("kingdomName");
    }

    public String randomName() {
        List<String> listOfNames = Arrays.asList(properties.getProperty("randomNames").split(","));
        int rand = new Random().nextInt(listOfNames.size());
        String name = listOfNames.get(rand);
        while (usedNames.contains(name)) {
            rand = new Random().nextInt(listOfNames.size());
            name = listOfNames.get(rand);
            if (usedNames.isEmpty()) {
                break;
            }
        }
        usedNames.add(name);
        return name;
    }

    public int qtyOfWizards() {
        return Integer.parseInt(properties.getProperty("wizardQuantity"));
    }

    public int qtyOfLords() {
        return Integer.parseInt(properties.getProperty("lordQuantity"));
    }

    public int qtyOfKnights() {
        return Integer.parseInt(properties.getProperty("knightQuantity"));
    }

    public int gameRounds() {
        return Integer.parseInt(properties.getProperty("gameRounds"));
    }

    public int qtyOfHeals() {
        return Integer.parseInt(properties.getProperty("qtyOfHeals"));
    }

    public double getHealthNumber(Hooman h) {
        double min = 0.0;
        double max = 0.0;
        switch (h.getTitle()) {
            case "King":
                min = Double.parseDouble(properties.getProperty("kingMinHealth"));
                max = Double.parseDouble(properties.getProperty("kingMaxHealth"));
                return (Math.random() * ((max - min) + 1)) + min;
            case "Lord":
                min = Double.parseDouble(properties.getProperty("lordMinHealth"));
                max = Double.parseDouble(properties.getProperty("lordMaxHealth"));
                return (Math.random() * ((max - min) + 1)) + min;
            case "Wizard":
                min = Double.parseDouble(properties.getProperty("wizardMinHealth"));
                max = Double.parseDouble(properties.getProperty("wizardMaxHealth"));
                return (Math.random() * ((max - min) + 1)) + min;
            case "Knight":
                min = Double.parseDouble(properties.getProperty("knightMinHealth"));
                max = Double.parseDouble(properties.getProperty("knightMaxHealth"));
                return (Math.random() * ((max - min) + 1)) + min;
            default:
                min = Double.parseDouble(properties.getProperty("peasantMinHealth"));
                max = Double.parseDouble(properties.getProperty("peasantMaxHealth"));
                return (Math.random() * ((max - min) + 1)) + min;
        }
    }

    public double getPowerNumber(Hooman h) {
        double min = 0.0;
        double max = 0.0;
        switch (h.getTitle()) {
            case "King":
                min = Double.parseDouble(properties.getProperty("kingMinPower"));
                max = Double.parseDouble(properties.getProperty("kingMaxPower"));
                return (Math.random() * ((max - min) + 1)) + min;
            case "Lord":
                min = Double.parseDouble(properties.getProperty("lordMinPower"));
                max = Double.parseDouble(properties.getProperty("lordMaxPower"));
                return (Math.random() * ((max - min) + 1)) + min;
            case "Wizard":
                min = Double.parseDouble(properties.getProperty("wizardMinPower"));
                max = Double.parseDouble(properties.getProperty("wizardMaxPower"));
                return (Math.random() * ((max - min) + 1)) + min;
            case "Knight":
                min = Double.parseDouble(properties.getProperty("knightMinPower"));
                max = Double.parseDouble(properties.getProperty("knightMaxPower"));
                return (Math.random() * ((max - min) + 1)) + min;
            default:
                min = Double.parseDouble(properties.getProperty("peasantMinPower"));
                max = Double.parseDouble(properties.getProperty("peasantMaxPower"));
                return (Math.random() * ((max - min) + 1)) + min;
        }
    }
}