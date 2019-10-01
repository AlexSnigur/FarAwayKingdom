package org.btarikool.javacourse;

public class Peasant extends Actions{
    private final static String TITLE = "Peasant";
    private final static double HEALTH = 0.9d;
    private final static int AUTHORITY = 2;
    private final static int STATUS_LEVEL = 3;
    private Human owner;

    public Peasant(int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human owner){
        super("#" + idFromOwnList, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom);
        this.owner = owner;
        kingdom.getPeasantList().add(this);
        kingdom.getHumanList().add(this);
        System.out.println(toString());
    }

    public void setOwner(Human owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "I'm - " + this.getTitleAndName() + ", my owner is " + this.owner.getTitleAndName() + ". My HP level: " + this.getHealPoints() + ". My authority level: " + this.getAuthorityPoints() + ".";
    }
}