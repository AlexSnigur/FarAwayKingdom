package org.btarikool.javacourse;


public class Wizard extends Hooman {
    private final static String TITLE = "Wizard";


    public Wizard(String name, int idNumber, Hooman chief) {
        super(name, idNumber, chief);
        this.title = TITLE;
        this.health = 1.0;
        this.power = 10;
        this.levelOfDominance = 2;
    }

    public void transformToKnight(Hooman human) {
    }

    public void healKing(Kingdom k) {
        King king = (King) this.chief;
        king.setHealth(king.getHealth() + 0.2);
        Peasant peasant = (Peasant) king.givePeasants(this, k);
        peasant.setHealth(getHealth() - 0.1);
        peasant.setPower(getPower() + 1);
        if(this.getSubordinateList().size()==5);{
           Knight knight = (Knight) peasant;

        }


    }
}