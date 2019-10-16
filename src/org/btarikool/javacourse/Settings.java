package org.btarikool.javacourse;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;


public class Settings {
    private static final String LOCAL_DIR = System.getProperty("user.dir").concat("\\conf\\config.properties");
    private static Properties prop = new Properties();
    private int iterationsCount;
    private int countOnStartKnight;
    private int countOnStartWizard;
    private int countOnStartLord;
    private int countOfWizardsHeals;
    private static List<Kingdom> kingdomsList = new ArrayList<>();

    public Settings(Kingdom kingdom) {
        clearLogFiles();
        kingdomsList.add(kingdom);
        setInputSettingsForStart(new BufferedReader(new InputStreamReader(System.in)));
    }

    static {
        try (FileInputStream input = new FileInputStream(LOCAL_DIR)){
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Properties file is not found!");
        }
    }

    public static List<Kingdom> getKingdomsList() {
        return kingdomsList;
    }

    private long getLogClearTime() {
        return Long.parseLong(prop.getProperty("clearTime", "0"));
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
        if (human instanceof Peasant) return 0.9d;
        else if (human instanceof Enemy) return 0.0d;
        else {
            double max = Double.parseDouble(prop.getProperty("maximumHpOnStart".concat(human.getTitle()), "0.0"));
            double min = Double.parseDouble(prop.getProperty("minimumHpOnStart".concat(human.getTitle()), "0.0"));
            return new Random().doubles(min, max).findFirst().getAsDouble();
        }
    }

    public int getAuth(Human human) {
        if (human instanceof Peasant) return 2;
        else if (human instanceof Enemy) return 0;
        else {
            int max = Integer.parseInt(prop.getProperty("maximumAuthorityOnStart".concat(human.getTitle()), "0"));
            int min = Integer.parseInt(prop.getProperty("minimumAuthorityOnStart".concat(human.getTitle()), "0"));
            return new Random().ints(min, (max + 1)).findFirst().getAsInt();
        }
    }


    public String getRandomName() {
        String[] names = prop.getProperty("randomNames").split(";");
        return names[new Random().ints(0, (names.length)).findFirst().getAsInt()];
    }

    public void setInputSettingsForStart(BufferedReader reader) {
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

    public static void writeLog() {
        SimpleDateFormat df = new SimpleDateFormat("_dd_hh_mm_ss");
        String date = df.format(new Date());
        String dir = System.getProperty("user.dir").concat("\\log\\kingdomsFight_" + date + ".log");
        try (BufferedWriter output = new BufferedWriter(new FileWriter(dir, true))) {
            for (Kingdom kingdom : kingdomsList) {
                output.write("RESULTS FOR KINGDOM ".concat(kingdom.getName().toUpperCase().concat("\n")));
                output.write(kingdom.getLog());
                kingdom.emptyLog();
            }
            output.write(("\nTHE WINNER IS KINGDOM " + Championship.getWinner().getKingdom().getName().toUpperCase().concat("!")));
            System.out.println("LOG WRITTEN");
        } catch (IOException e) {}
    }

    private void clearLogFiles() {
        Path path = Paths.get(System.getProperty("user.dir").concat("\\log\\")).toAbsolutePath();
        try {
            Files.walkFileTree(path,  EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1, new MyVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyVisitor extends SimpleFileVisitor {
        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile() && attrs.creationTime().toMillis() < (new Date().getTime() - getLogClearTime())) {
                Files.delete(((Path)file));
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
