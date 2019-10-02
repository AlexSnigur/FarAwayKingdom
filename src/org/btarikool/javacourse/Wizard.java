// King.java
package org.btarikool.javacourse;

public class Wizard extends Person {
    private static String TITLE = "Wizard ";


    public Wizard(String name) {
        super(name);
        this.title = TITLE;
        this.setHealth(1.0);
        this.power = 10;
        this.actions = new String[2][2];
    }

    public Peasant transformToKnight(String masterName) {
        return null;
//        return new Peasant(masterName + "'s peasant #" + (10 - peasants));
    }

    public void cureKing(Kingdom kingdom) {
        King king = (King) this.chief;
        king.setHealth(king.getHealth() + 0.2);
        king.createPeasantWithName(this, kingdom);

    }

}