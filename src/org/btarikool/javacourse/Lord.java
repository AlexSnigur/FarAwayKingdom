package org.btarikool.javacourse;

public class Lord extends Human {
    private final static String TITLE = "Lord";
    private final static double HEALTH = 0.7d;
    private final static int AUTHORITY = 5;
    private final static int STATUS_LEVEL = 2;
    private static String[][] phrase = new String[][] {
            {"loyalty", "military aid"},
            {"shelter", "protection"}
    };

    public Lord(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief) {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, phrase, idFromCollectiveList, idFromOwnList, kingdom, chief);
    }
}