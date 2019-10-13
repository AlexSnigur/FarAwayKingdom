package org.btarikool.javacourse;

public class Peasant extends Knight{
    private final static String TITLE = "Peasant";
    private final static double HEALTH = 0.9d;
    private final static int AUTHORITY = 2;
    private final static int STATUS_LEVEL = 4;
    private static String[][] phrase = new String[][] {
        {"farm the land", "pay rent"},
        {null, null}
    };

    public Peasant(int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief){
        super("#" + getId(), TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, phrase, idFromCollectiveList, idFromOwnList, kingdom, chief);
        kingdom.getPeasantList().add(this);
        kingdom.getHumanList().add(this);
        kingdom.getHumanDubList().add(this);
    }
}