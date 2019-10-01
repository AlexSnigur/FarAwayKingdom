package org.btarikool.javacourse;

public class Knight extends Human {
    private final static String TITLE = "Knight";
    private final static double HEALTH = 0.7d;
    private final static int AUTHORITY = 5;
    private final static int STATUS_LEVEL = 2;

    public Knight(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief) {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom, chief);
        System.out.println(toString());
    }

}