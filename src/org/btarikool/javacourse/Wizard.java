package org.btarikool.javacourse;


public class Wizard extends Hooman {
    private final static String TITLE = "Wizard";


    public Wizard(String name, int idNumber, Hooman chief) {
        super(name, idNumber,chief);
        this.title = TITLE;
        this.health = 1.0;
        this.power = 10;
        this.levelOfDominance = 1;
    }

    public void transformToKnight(Hooman human) {

    }

    public void healKing() {
        King theKing = (King) this.getChief();
        theKing.setHealth(theKing.getHealth() +0.2);

    }
}