// Kingdom.java
package org.btarikool.javacourse;

public class Game {

      public static void main(String[]args) {
          Kingdom farAway = createKindomAndPrepare("Far Away Kingdom");
          Kingdom


          static void createKindomAndPrepare (String kingdom Name);
          int lordsCount = Settings.getLordsCount();


          Kingdom kingdom = new Kingdom("FarAway");
          King richard = (King) kingdom.createPerson("Richard", "King", null);
        for (int i = 0; i< Settings.getLordsCount(); i++){
        Lord lord = (Lord) kingdom
    }
    Lord lionel = (Lord) kingdom.createPerson("Lionel", "Lord", richard);
    Lord lee = (Lord) kingdom.createPerson("Lee", "Lord", richard);
    Wizard wally = (Wizard) kingdom.createPerson("Wally","wizard", richard);
    Knight keeney = (Knight) kingdom.createPerson("Keeney", "KNIGHT", lionel);
    Knight koosey = (Knight) kingdom.createPerson("koosey", "knight", lee);

        for(int i = 1; i <=Settings.getWizardsCount(); i++){
        Wizard wizard = (Wizard) kingdom.createPerson("Wizard #" + i, "Wizard", richard);
    }
        for (int i = 1; i <= lordsCount; i++) {
        Lord lord = (Lord) kingdom.createPerson("Lord #" + i, "Lord", richard);
        Knight knight = (Knight) kingdom.createPerson("Knight #" + i, "Knight", lord);
        System.out.println("LORDS COUNT = " + lordsCount);
    }
        System.out.println(kingdom);

             kingdom.runActionsUp();
        kingdom.runActionsDown();

        keeney.setHealth(0.1);
    keeney.isDead = true;
        System.out.println(kingdom);
        kingdom.saveKingdomState();
        for (int i = 0; i < Settings.getKingCureCount(); i++){
        kingdom.getFirstWizard().cureKing(kingdom);
    }
        System.out.println(kingdom);

        Kingdom1.saveKingdomState();
        System.out.println(kingdom);


        for (int i = 0; i < 34; i++){
        wally.cureKing(kingdom);
    }
        System.out.println(kingdom);

    Knight[] pair = kingdom.chooseRandomPair();
        kingdom.doFight(pair);

}
    void runGameCycle(Kingdom kingdom) {
        kingdom.runActionsUp();
        kingdom.runActionsDown();

        kingdom.saveKingdomState();
        for (int i = 0; i < Settings.getKingCureCount(); i++){
            kingdom.getFirstWizard()
        }

    }

}
