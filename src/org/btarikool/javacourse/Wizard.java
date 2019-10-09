package org.btarikool.javacourse;

public class Wizard extends Human {

    private final static String TITLE = "Wizard";
    private final static double HEALTH = 1.0d;
    private final static  int AUTHORITY = 10;
    private final static int STATUS_LEVEL = 4;
    private final static double HP_TO_KING = 0.2d;

    public Wizard(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief) {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom, chief);
        System.out.println(toString());
    }

    public void doToHeal(King king) {
        System.out.println(this.getTitleAndName() + " heals " + king.getTitleAndName());
        king.setHealPoints(king.getHealPoints() + HP_TO_KING);
        if (king.getMyPeasantsList().size()==0) king.createPeasant();
        king.doPresentPeasant(this);
        Peasant thisPeasant = this.getMyPeasantsList().get(this.getMyPeasantsList().size() - 1);
        thisPeasant.setHealPoints(thisPeasant.getHealPoints() - 0.1d);
        thisPeasant.setAuthorityPoints(thisPeasant.getAuthorityPoints() + 1);
        if (this.getMyPeasantsList().size()==5) {
            Knight newKnight = this.getMyPeasantsList().get(0);
            newKnight.setHealPoints(getRandomHP());
            newKnight.setAuthorityPoints(getRandomAuth());
            newKnight.setName("EP" + thisPeasant.getName());
            newKnight.setTitle("Knight");
            newKnight.setStatusLevel(2);
            setRank(newKnight);
            newKnight.setOwnListId(this.getKingdom().getKnightList().size());
            this.getKingdom().getKnightList().add(newKnight);
            this.getKingdom().getPeasantList().remove(newKnight);
            this.getMyPeasantsList().remove(newKnight);
        }
    }

    private double getRandomHP() {
        double base = 0.3;
        double random = Math.random() * 0.6;
        return base + random;
    }

    private int getRandomAuth() {
        int base = 4;
        int random = (int) (Math.random() * 5);
        return base + random;
    }
}
