// King.java
package org.btarikool.javacourse;

public class Wizard extends Person {
    public static String TITLE = "Wizard ";


    public Wizard(String name) {
        super(name);
        this.title = TITLE;
        this.setHealth(1.0);
        this.power = 10;
        this.actions = new String[2][2];
    }

    public Knight transformToKnight(Peasant peasant, Kingdom kingdom) {
        Knight newKnight = new Knight("Young Knight #" + peasant.id);
        kingdom.people[peasant.id] = newKnight;
        newKnight.setId(peasant.id);
        newKnight.setHealth(getRandomHealth());
        newKnight.power = getRandomPower();
        newKnight.setChief(this);
        return newKnight;
    }

    private double getRandomHealth() {
        double base = 0.3;
        double random = Math.random() * 0.6; // from 0.0 to 0.6
        return base + random;
    }

    private int getRandomPower() {
        int base = 4;
        int random = (int) (Math.random() * 5);
        return base + random;
    }

    public void cureKing(Kingdom kingdom) {
        King king = (King) this.chief;
        king.setHealth(king.getHealth() + 0.2);
        Person peasant  = king.createPeasantWithName(this, kingdom);
        peasant.setHealth(peasant.getHealth() - 0.1);
        peasant.power++;
        int peasantsNum = this.subordinates.length;
        boolean isTimeToCreateKnight = this.subordinates.length % 5 == 0;
        if(isTimeToCreateKnight) {
            Knight newbie = transformToKnight((Peasant) peasant, kingdom);
            this.subordinates[this.subordinates.length - 1] = newbie;
        }
    }

}