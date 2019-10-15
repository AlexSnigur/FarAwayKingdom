import java.util.Properties;

public class Settings {
    static Properties properties = new Properties();
    String countStr = properties.getProperty("Wizards.count");
        int count = Integer.parseInt(countStr);
    


    public static int[] getLordsPowerInterval(String propertyName)) {
        String interval = properties.getProperty("lord.power.inteval");
        String[] intervalArr = interval.split("-"); // 7-9 => {"7", "9"}
        int[] ret = new int[2];
        ret[0] = Integer.valueOf(intervalArr[0]);
        ret[1] = Integer.valueOf(intervalArr[1]);
        return ret;
    }

}
