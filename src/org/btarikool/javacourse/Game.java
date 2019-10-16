// Kingdom.java
package org.btarikool.javacourse;

public class Game {

    public static void main(String[] args) {
        Kingdom farAway = createKingdomAndPrepare("Far Away");
        Kingdom notSoFar = createKingdomAndPrepare("Not So Far");
    }

    static Kingdom createKingdomAndPrepare(String kingdomName) {
        int lordsCount = Settings.getLordsCount();
        Kingdom kingdom = new Kingdom(kingdomName);
        King richard = (King) kingdom.createPerson("Richard", "King", null);
        for(int i = 1; i <= Settings.getWizardsCount(); i++) {
            Wizard wizard = (Wizard) kingdom.createPerson("Wizard #" + i, "Wizard", richard);
        }
        for (int i = 1; i <= lordsCount; i++) {
            Lord lord = (Lord) kingdom.createPerson("Lord #" + i, "Lord", richard);
            Knight knight = (Knight) kingdom.createPerson("Knight #" + i, "Knight", lord);
        }
        for (int i = 0; i < Settings.getGamerunsCount(); i++) {
            runGameCycle(kingdom);
            System.out.println("**** RUN # " + i + " ******");
            System.out.println(kingdom);
        }
        return kingdom;
    }

    static void runGameCycle(Kingdom kingdom) {
        kingdom.runActionsUp();
        kingdom.runActionsDown();
        for (int i = 0; i < Settings.getKingCureCount(); i++){
            kingdom.getFirsWizard().cureKing(kingdom);
        }
        kingdom.saveKingdomState();
    }

}
