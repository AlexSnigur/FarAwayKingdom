// Kingdom.java
package org.btarikool.javacourse;

public class Game {

    public static void main(String[] args) {
        Kingdom farAway = createKingdomAndPrepare("Far Away");
        Knight first = farAway.getFirstAliveKnight();
        Kingdom notSoFar = createKingdomAndPrepare("Not So Far");
        Knight second = notSoFar.getFirstAliveKnight();
        Kingdom[] kingdomPair = new Kingdom[] {farAway, notSoFar};
        Knight[] pair = new Knight[]{first, second};
        Knight winner = Kingdom.doFight(pair);
        Kingdom loserKingdom = kingdomPair[0] == winner.getKingdom() ?
                            kingdomPair[1] : kingdomPair[0];
        farAway.saveKingdomState(1000);
        notSoFar.saveKingdomState(1000);

        Knight third = loserKingdom.getFirstAliveKnight();
        pair = new Knight[]{winner, third};
        winner = Kingdom.doFight(pair);
        loserKingdom = kingdomPair[0] == winner.getKingdom() ?
                kingdomPair[1] : kingdomPair[0];
        System.out.println("WINNER IS " + winner);
        farAway.saveKingdomState(2000);
        notSoFar.saveKingdomState(2000);
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
            runGameCycle(kingdom, i);
            System.out.println("**** RUN # " + i + " ******");
            System.out.println(kingdom);
        }
        return kingdom;
    }

    static void runGameCycle(Kingdom kingdom, int cycleNum) {
        kingdom.runActionsUp();
        kingdom.runActionsDown();
        for (int i = 0; i < Settings.getKingCureCount(); i++){
            kingdom.getFirsWizard().cureKing(kingdom);
        }
        kingdom.saveKingdomState(cycleNum);
    }

}
