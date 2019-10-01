package org.btarikool.javacourse;

public class Lord extends Actions {
    private final static String TITLE = "Lord";
    private final static double HEALTH = 0.7d;
    private final static int AUTHORITY = 5;
    private final static int STATUS_LEVEL = 2;

    public Lord(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom) {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom);
        System.out.println(toString());
    }
}