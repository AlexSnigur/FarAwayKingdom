package org.btarikool.javacourse;

public class Peasant extends Actions{
    private final static String TITLE = "Peasant";
    private final static double HEALTH = 0.9d;
    private final static int AUTHORITY = 2;
    private final static int STATUS_LEVEL = 3;

    public Peasant(int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief){
        super("#" + idFromOwnList, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom, chief);
        kingdom.getPeasantList().add(this);
        kingdom.getHumanList().add(this);
        System.out.println(toString());
    }


    @Override
    public String toString() {
        return "I'm - " + this.getTitleAndName() + ". My HP level: " + this.getHealPoints() + ". My authority level: " + this.getAuthorityPoints() + ". My chief is " + this.getChief().getTitleAndName() +".";
    }
}