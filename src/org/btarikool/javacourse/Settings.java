package org.btarikool.javacourse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    static Properties properties;

    static {
        properties = new Properties();
        try(InputStream input = new FileInputStream("conf/default.properties")) {
            properties.load(input);
        } catch(IOException ioe) {
            System.out.println("properties file not found");
        }

    }

    public static int getLordsCount() {
        String countStr = properties.getProperty("lords.count");
        int count = Integer.parseInt(countStr);
        return count;
    }

    public static int getKnigtsCount() {
        String countStr = properties.getProperty("knights.count");
        int count = Integer.parseInt(countStr);
        return count;
    }

    public static int getWizardsCount() {
        String countStr = properties.getProperty("wizards.count");
        int count = Integer.parseInt(countStr);
        return count;
    }

    public static int[] getLordsPowerInterval () {
        String interval = properties.getProperty("lord.power.interval");
        String[] intervalArr = interval.split("-"); // 7-9 => {"7", "9"}
        int[] ret = new int[2]; //  {0, 0}
        ret[0] = Integer.parseInt(intervalArr[0]);// {7, 0}
        ret[1] = Integer.parseInt(intervalArr[1]); // {7, 9}
        return ret;
    }

    public static double[] getLordsHealthInterval () {
        String interval = properties.getProperty("lord.health.interval");
        String[] intervalArr = interval.split("-"); // 0.7-0.9 => {"0.7", "0.9"}
        double[] ret = new double[2]; //  {0.0, 0.0}
        ret[0] = Double.valueOf(intervalArr[0]);// {0.7, 0.0}
        ret[1] = Double.valueOf(intervalArr[1]); // {0.7, 0.9}
        return ret;
    }




}
