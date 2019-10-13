package org.btarikool.javacourse;

import java.io.*;
import java.util.Properties;
import java.util.Random;


public class Settings {

    private static final String LOCAL_DIR = System.getProperty("user.dir").concat("\\conf\\config.properties");
    private static Properties prop = new Properties();
    private int countOnStartKnight;
    private int iterationsCount;
    private int countOnStartWizard;
    private int countOnStartLord;
    private int countOfWizardsHeals;

    static {
        try (FileInputStream input = new FileInputStream(LOCAL_DIR)){
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Properties file is not found!");
        }
    }

    public int getIterCount() {
        return this.iterationsCount;
    }

    public int getCountOnStartKnight() {
        return countOnStartKnight;
    }

    public int getCountOnStartWizard() {
        return countOnStartWizard;
    }

    public int getCountOnStartLord() {
        return countOnStartLord;
    }

    public int getCountOfWizardsHeals() {
        return countOfWizardsHeals;
    }

    public double getHP(Human human) {
        double max = Double.parseDouble(prop.getProperty("maximumHpOnStart".concat(human.getTitle()), "0.0"));
        double min = Double.parseDouble(prop.getProperty("minimumHpOnStart".concat(human.getTitle()), "0.0"));
        return new Random().doubles(min, max).findFirst().getAsDouble();
    }

    public int getAuth(Human human) {
        int max = Integer.parseInt(prop.getProperty("maximumAuthorityOnStart".concat(human.getTitle()), "0"));
        int min = Integer.parseInt(prop.getProperty("minimumAuthorityOnStart".concat(human.getTitle()), "0"));
        return new Random().ints(min, (max + 1)).findFirst().getAsInt();
    }


    public String getRandomName() {
        String[] names = prop.getProperty("randomNames").split(";");
        return names[new Random().ints(0, (names.length)).findFirst().getAsInt()];
    }

    public void setInputSettingsForStart() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        this.iterationsCount = setPropInt(reader, "Please enter count of iterations for our actions: ");
        this.countOnStartLord = setPropInt(reader, "Please enter Lords count on start: ");
        this.countOnStartKnight = setPropInt(reader, "Please enter Knights count on start: ");
        this.countOnStartWizard = setPropInt(reader, "Please enter Wizards count on start: ");
        this.countOfWizardsHeals = setPropInt(reader, "Please enter Wizards heals count: ");
        System.out.print("Well done! Game will start in a ");
        try {
            for (int x = 3; x > 0; x--) {
                System.out.print(x + "...");
                Thread.sleep(1000);
            }
            System.out.println("\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int setPropInt(BufferedReader reader, String msg) {
        int num = 0;
        try {
            String outputString;
            System.out.print(msg);
            while (!(outputString = reader.readLine()).matches("[0-9]+")){
                System.out.print("You entered something else, but not an integer! Please try again: ");
            }
            num = Integer.parseInt(outputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }


}
