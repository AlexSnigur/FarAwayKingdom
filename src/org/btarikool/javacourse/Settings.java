package org.btarikool.javacourse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    static Properties properties;

    static {
        properties = new Properties();
        try(InputStream input = new FileInputStream("conf/deafult.properties")) {
            properties.load(input);
            String lordsCount = properties.getProperty("lords.count");
        } catch (IOException ioe) {
            System.out.println("properties file not found");
        }
    }

    public static int getLordsCount() {
        String countStr = properties.getProperty("lords.count");
        int count = Integer.parseInt(countStr);
        return count;
    }

    public static int getWizardCount() {
        String countStr = properties.getProperty("wizard.count");
        int count = Integer.parseInt(countStr);
        return count;
    }

    public static int[] getLordsPowerInterval() {
        String interval = properties.getProperty("lords.power.interval");
        String[] intervalArr = interval.split("-");
        int[] ret = new int[2];
        ret[0] = Integer.parseInt(intervalArr[0]);
        ret[1] = Integer.parseInt(intervalArr[1]);
        return ret;
    }

    public static double[] getLordsHealthInterval() {
        String interval = properties.getProperty("lords.health.interval");
        String[] intervalArr = interval.split("-");
        double[] ret = new double[2];
        ret[0] = Double.valueOf(intervalArr[0]);
        ret[1] = Double.valueOf(intervalArr[1]);
        return ret;
    }
}


