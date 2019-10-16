
// Kingdom.java
package org.btarikool.javacourse;

public class Game {

    public static void main(String[] args) {
        int lordsCount = Settings.getLordsCount();
        Kingdom kingdom1 = new Kingdom("FarAway");
        King richard = (King) kingdom1.createPerson("Richard", "King", null);
        for(int i = 1; i <= Settings.getWizardsCount(); i++) {
            Wizard wizard = (Wizard) kingdom1.createPerson("Wizard #" + i, "Wizard", richard);
        }
        for (int i = 1; i <= lordsCount; i++) {
            Lord lord = (Lord) kingdom1.createPerson("Lord #" + i, "Lord", richard);
            Knight knight = (Knight) kingdom1.createPerson("Knight #" + i, "Knight", lord);
        }

        System.out.println(kingdom1);

        kingdom1.runActionsUp();
        kingdom1.runActionsDown();

        System.out.println(kingdom1);
        kingdom1.saveKingdomState();
        for (int i = 0; i < Settings.getKingCureCount(); i++){
            kingdom1.getFirsWizard().cureKing(kingdom1);
        }

        System.out.println(kingdom1);


/*
        kingdom1.saveKingdomState();
        System.out.println(kingdom1);
        for (int i = 0; i < 34; i++){
            wally.cureKing(kingdom1);
        }
        System.out.println(kingdom1);
        Knight[] pair = kingdom1.chooseRandomPair();
        kingdom1.doFight(pair);
*/
    }

}
