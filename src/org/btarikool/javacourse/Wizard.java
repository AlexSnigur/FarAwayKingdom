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
        king.setHealPoints(king.getHealPoints() + HP_TO_KING);
        if (king.getMyPeasantsList().size()==0) {
            king.createPeasant();
            king.doPresentPeasant(this);
            Peasant thisPeasant = this.getMyPeasantsList().get(this.getMyPeasantsList().size()-1);
            thisPeasant.setHealPoints(thisPeasant.getHealPoints()- 0.1d);
            thisPeasant.setAuthorityPoints(thisPeasant.getAuthorityPoints() + 1);
        }
        else {
            king.doPresentPeasant(this);
            Peasant thisPeasant = this.getMyPeasantsList().get(this.getMyPeasantsList().size() - 1);
            thisPeasant.setHealPoints(thisPeasant.getHealPoints() - 0.1d);
            thisPeasant.setAuthorityPoints(thisPeasant.getAuthorityPoints() + 1);
        }

        if (this.getMyPeasantsList().size()==5) {
            Knight thisPeasant = this.getMyPeasantsList().get(0);
            thisPeasant.setHealPoints(0.7d);
            thisPeasant.setAuthorityPoints(5);
            thisPeasant.setName("EP" + thisPeasant.getName());
            thisPeasant.setTitle("Knight");
            thisPeasant.setStatusLevel(2);
            setRank(thisPeasant);
            thisPeasant.setOwnListId(this.getKingdom().getKnightList().size());
            this.getKingdom().getKnightList().add(thisPeasant);
            this.getKingdom().getPeasantList().remove(thisPeasant);
            this.getMyPeasantsList().remove(thisPeasant);
        }
    }
}
