package org.btarikool.javacourse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    static Properties properties = new Properties();

    static {
        try(InputStream input = new FileInputStream("conf/default.properties")) {
            properties.load(input);
        } catch(IOException ioe) {
            System.out.println("properties file not found");
        }

    }

    public static int getLordsCount() {
        return getIntValue("lords.count");
    }

    public static int getKnigtsCount() {
        return getIntValue("knights.count");
    }

    public static int getWizardsCount() {
        return getIntValue("wizards.count");
    }

    public static int[] getLordsPowerInterval () {
        return getPowerInterval("lord.power.interval");
    }

    public static int[] getKnightsPowerInterval () {
        return getPowerInterval("knight.power.interval");
    }

    public static int[] getWizardsPowerInterval () {
        return getPowerInterval("wizard.power.interval");
    }

    public static int[] getPeasantsPowerInterval () {
        return getPowerInterval("peasant.power.interval");
    }

    private static int[] getPowerInterval(String propertyName) {
        String interval = properties.getProperty(propertyName);
        String[] intervalArr = interval.split("-"); // 7-9 => {"7", "9"}
        int[] ret = new int[2]; //  {0, 0}
        ret[0] = Integer.parseInt(intervalArr[0]);// {7, 0}
        ret[1] = Integer.parseInt(intervalArr[1]); // {7, 9}
        return ret;
    }

    static double[] getLordsHealthInterval () {
        return getHealthInterval("lord.health.interval");
    }
    static double[] getKnightsHealthInterval () {
        return getHealthInterval("knight.health.interval");
    }

    static double[] getWizardsHealthInterval () {
        return getHealthInterval("wizard.health.interval");
    }

    static double[] getPeasantsHealthInterval () {
        return getHealthInterval("peasant.health.interval");
    }

    static double[] getHealthInterval (String propertyName) {
        String interval = properties.getProperty(propertyName);
        String[] intervalArr = interval.split("-"); // 0.7-0.9 => {"0.7", "0.9"}
        double[] ret = new double[2]; //  {0.0, 0.0}
        ret[0] = Double.valueOf(intervalArr[0]);// {0.7, 0.0}
        ret[1] = Double.valueOf(intervalArr[1]); // {0.7, 0.9}
        return ret;
    }

    static int getGamerunsCount () {
        String countStr = properties.getProperty("gameruns.count");
        return Integer.parseInt(countStr);
    }

    static int getIntValue(String propertyName) {
        try {
            return Integer.parseInt(properties.getProperty(propertyName));
        } catch (Exception e) {
            System.out.println("Invalid property value: " + propertyName);
            return 0;
        }

    }
    static int getKingCureCount () {
        return getIntValue("king.cure.count");
    }




}

