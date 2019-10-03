package org.btarikool.javacourse;

public class King extends Human {

    private final static String TITLE = "King";
    private final static double HEALTH = 0.5d;
    private final static  int AUTHORITY = 10;
    private final static int STATUS_LEVEL = 1;
    //private final int NUM_OF_PEASANTS_ON_START = 10;

    public King(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom) {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom, null);
        System.out.println(toString());
        //for (int x = 0; x < NUM_OF_PEASANTS_ON_START; x++) this.createPeasant();
    }
    public Peasant createPeasant() {
        System.out.print(this.getTitleAndName() + " creates new peasant: ");
        Peasant peasant = new Peasant(this.getKingdom().getHumanList().size(), this.getKingdom().getPeasantList().size(), this.getKingdom(), this);
        this.getMyPeasantsList().add(peasant);
        return peasant;
    }
}