// Kingdom.java
package org.btarikool.javacourse;

import com.sun.scenario.Settings;

public class Game {

    public static void main(String[] args) {
        int lordsCount = Settings.getLordsCount();
        King richard = (King) kingdom1.createPerson( "Richard",  "King",  null);
        for (int i = (Lord) kingdom1.createPerson( "Lord #" + i,  "Lord", richard);
             knight = (Knight) kingdom1.createPerson(  "Knight #" + i,  "Knight", lord);
    }
    for (int i = 1; i <= Settings.getWizardCount(); i++) {
        Wizard wizard = (Wizard) kingdom1.createPerson( "Wizard #" + i,  "Wizard", richard);
    }
    System.out.println(kingdom1);

        Kingdom kingdom1 = new Kingdom("FarAway");
        King richard = (King) kingdom1.createPerson("Richard", "King", null);
        Lord lionel = (Lord) kingdom1.createPerson("Lionel", "Lord", richard);
        Lord lee = (Lord) kingdom1.createPerson("Lee", "Lord", richard);
        Wizard wally = (Wizard) kingdom1.createPerson("Wally", "wizard", richard);
        Knight keeney = (Knight) kingdom1.createPerson("Keeney", "kNIGHT", lionel);
        Knight koosey = (Knight) kingdom1.createPerson("koosey", "knight", lee);
        kingdom1.runActionsUp();
        kingdom1.runActionsDown();
        keeney.setHealth(0.1);
        keeney.isDead = true;
        kingdom1.saveKingdomState();
        System.out.println(kingdom1);
        for (int i = 0; i < 34; i++){
            wally.cureKing(kingdom1);
        }
        System.out.println(kingdom1);
        Knight[] pair = kingdom1.chooseRandomPair();
        kingdom1.doFight(pair);

    }

}
