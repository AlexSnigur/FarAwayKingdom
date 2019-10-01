package org.btarikool.javacourse;

public class Knight extends Actions {
    private final static String TITLE = "Knight";
    private final static double HEALTH = 0.7d;
    private final static int AUTHORITY = 5;
    private final static int STATUS_LEVEL = 2;

    public Knight(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom) {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom);
        System.out.println(toString());
    }

}