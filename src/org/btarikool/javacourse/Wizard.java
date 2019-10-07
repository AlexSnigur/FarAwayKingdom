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




//        return new Peasant(masterName + "'s peasant #" + (10 - peasants));

    public Knight transformKnight (Peasant peasant, Kingdom kingdom) {
        Knight newKnight = new Knight( "Young Knight #" + peasant.id);
        kingdom.people [peasant.id]=newKnight;
        newKnight.setId(peasant.id);
        newKnight.setChief(this);
        return newKnight;
    }
    public void cureKing(Kingdom kingdom) {
        King king = (King) this.chief;
        king.setHealth(king.getHealth() + 0.2);
        Person peasant  = king.createPeasantWithName(this, kingdom);
        peasant.setHealth(peasant.getHealth() - 0.1);
        peasant.power++;
        int peasantsNum = this.subordinates.length;
        boolean  isTimeToCreateKnight = this.subordinates.length % 5 ==0;
        if(isTimeToCreateKnight)

       if(peasantsNum == 5) {
           Knight newbie = transformKnight ((Peasant) peasant, kingdom);
           this.subordinates[this.subordinates.length - 1] = newbie;
    }

}