package org.btarikool.javacourse;

public class Wizard extends Human {

    private final static String TITLE = "Wizard";
    private final static double HEALTH = 1.0d;
    private final static  int AUTHORITY = 10;
    private final static int STATUS_LEVEL = 2;
    private final static double HP_TO_KING = 0.2d;
    private static String[][] phrase = new String[][] {
            {"loyalty", "military aid"},
            {"shelter", "protection"}
    };

    public Wizard(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief) {
        super(name, TITLE, STATUS_LEVEL, phrase, idFromCollectiveList, idFromOwnList, kingdom, chief);
    }

    public void doToHeal(King king) {
        int healCount = this.getKingdom().getSettings().getCountOfWizardsHeals();
        while (healCount > 0) {
            doToHealIter(king);
            healCount--;
        }
    }

    public void doToHealIter(King king) {
        if (king.getHealPoints() <= HP_TO_KING) {
            System.out.println(this.getTitleAndName() + " heals " + king.getTitleAndName());
            king.setHealPoints(king.getHealPoints() + HP_TO_KING);
            king.doPresentPeasant(this);
            Peasant thisPeasant = this.getMyPeasantsList().get(this.getMyPeasantsList().size() - 1);
            thisPeasant.setHealPoints(thisPeasant.getHealPoints() - 0.1d);
            thisPeasant.setAuthorityPoints(thisPeasant.getAuthorityPoints() + 1);
        }
        this.createKnightFromPeasant();
    }

    private void createKnightFromPeasant() {
        if (this.getMyPeasantsList().size() > 4) {
            Knight newKnight = this.getMyPeasantsList().get(0);
            newKnight.setHealPoints(getRandomHP());
            newKnight.setAuthorityPoints(getRandomAuth());
            newKnight.setName(this.getKingdom().getSettings().getRandomName().concat(" © " + this.getTitleAndName()));
            newKnight.setTitle("Knight");
            newKnight.setStatusLevel(2);
            setRank(newKnight);
            newKnight.setOwnListId(this.getKingdom().getKnightList().size());
            System.out.println(this.getTitleAndName() + " creates new " + newKnight.getTitleAndName());
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
