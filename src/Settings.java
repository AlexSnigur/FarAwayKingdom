public class Settings {
    String countStr = properties.getProperty("Wizards.count");
        int count = Integer.parseInt(countStr);
    return count;
    }

    public static int[] getLordsPowerInterval {
        String interval = properties.getProperty("lord.power.inteval");
        String[] intervalArr = interval.split( regex "-"); // 7-9 => {"7", "9"}
        int[] ret = new int[2];
        ret[0] = Double.valueOf(intervalArr[0]);
        ret[1] = Double.valueOf(intervalArr[1]);
    }

}
