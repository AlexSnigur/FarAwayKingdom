// Kingdom.java
package org.btarikool.javacourse;

public class Game {
    private static Kingdom[] allKingdoms = new Kingdom[2];

    public static void main(String[] args) {
        Kingdom farAway = createKingdomAndPrepare("Far Away");
        allKingdoms[0] = farAway;
        Knight first = farAway.getFirstAliveKnight();
        Kingdom notSoFar = createKingdomAndPrepare("Not So Far");
        allKingdoms[1] = notSoFar;
        doFightAndReturnWinner(first, 0);
    }

    private static void doFightAndReturnWinner(Knight currentWinner, int level) {
        Kingdom first = currentWinner.getKingdom();
        Kingdom second = allKingdoms[0] == first ? allKingdoms[1] : allKingdoms[0];
        Knight secondKnight = second.getFirstAliveKnight();
        if (secondKnight == null ) {
            System.out.println("!!! Wins kingdom: " + currentWinner.getKingdom().getName());
            return;
        }
        Knight[] pair = new Knight[]{currentWinner, secondKnight};
        Knight winner = Kingdom.doFight(pair);
        first.saveKingdomState(1000 + level);
        second.saveKingdomState(1000 + level);
        doFightAndReturnWinner(winner, ++level);
    }


    private static Kingdom createKingdomAndPrepare(String kingdomName) {
        int lordsCount = Settings.getLordsCount();
        Kingdom kingdom = new Kingdom(kingdomName);
        King richard = (King) kingdom.createPerson("Richard", "King", null);
        for(int i = 1; i <= Settings.getWizardsCount(); i++) {
            kingdom.createPerson("Wizard #" + i, "Wizard", richard);
        }
        for (int i = 1; i <= lordsCount; i++) {
            Lord lord = (Lord) kingdom.createPerson("Lord #" + i, "Lord", richard);
            kingdom.createPerson("Knight #" + i, "Knight", lord);
        }
        for (int i = 0; i < Settings.getGamerunsCount(); i++) {
            runGameCycle(kingdom, i);
            System.out.println("**** RUN # " + i + " ******");
            System.out.println(kingdom);
        }
        return kingdom;
    }

    private static void runGameCycle(Kingdom kingdom, int cycleNum) {
        kingdom.runActionsUp();
        kingdom.runActionsDown();
        for (int i = 0; i < Settings.getKingCureCount(); i++){
            kingdom.getFirsWizard().cureKing(kingdom);
        }
        kingdom.saveKingdomState(cycleNum);
    }

}
