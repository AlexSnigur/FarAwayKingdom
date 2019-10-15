// Kingdom.java
package org.btarikool.javacourse;

public class Game {

    public static void main(String[] args) {

        Kingdom kingdom1 = new Kingdom("Far away");

        King richard = (King) kingdom1.createPerson("Richard", "King", null);
        for(int i = 0; i< Settings.getLordsCount(); i++){
            Lord lord = (Lord) kingdom1.createPerson("Lord £" +i, "Lord", richard);
            Knight knight = (Knight) kingdom1.createPerson("knight £" +i, "Knight", lord);
        }
        for(int i = 1; i<=Settings.getWizardsCount(); i++){
            Wizard wizard = (Wizard) kingdom1.createPerson("Wizard £" +i, "Wizard", richard);
        }
        System.out.println(kingdom1);
        /*int lordsCount = Settings.getLordsCount();
        System.out.println("LORDS COUNT = " +lordsCount);*/
        System.out.println("LORDS COUNT: " + Settings.getLordsCount());
        System.out.println("LORDS HEALTH INTERVAL FROM: " + Settings.getLordsHealthInterval()[0]);
        System.out.println("LORDS HEALTH INTERVAL TO: " + Settings.getLordsHealthInterval()[1]);
        System.out.println("LORDS POWER INTERVAL FROM: "+ Settings.getLordsPowerInterval()[0]);
        System.out.println("LORDS POWER INTERVAL TO:" + Settings.getLordsPowerInterval()[1]);
        System.out.println("KNIGHTS COUNT: " + Settings.getKnightsCount());
        System.out.println("KNIGHTS HEALTH INTERVAL FROM: " + Settings.getKnightsHealthInterval()[0]);
        System.out.println("KNIGHTS HEALTH INTERVAL TO: " + Settings.getKnightsHealthInterval()[1]);
        System.out.println("KNIGHTS POWER INTERVAL FROM: " + Settings.getKnightsPowerInterval()[0]);
        System.out.println("KNIGHTS POWER INTERVAL TO: " + Settings.getKnightsPowerInterval()[1]);
        System.out.println("WIZARDS COUNT: " + Settings.getWizardsCount());
        System.out.println("WIZARDS HEALTH INTERVAL FROM: " + Settings.getWizardsHealthInterval()[0]);
        System.out.println("WIZARDS HEALTH INTERVAL TO: " + Settings.getWizardsHealthInterval()[1]);
        System.out.println("WIZARDS POWER INTERVAL FROM: " + Settings.getWizardsPowerInterval()[0]);
        System.out.println("WIZARDS POWER INTERVAL TO: " + Settings.getWizardsPowerInterval()[1]);
        System.out.println("PEASANTS COUNT " + Settings.getPeasantsCount());
        System.out.println("PEASANTS HEALTH INTERVAL FROM: " + Settings.getPeasantsHealthInterval()[0]);
        System.out.println("PEASANTS HEALTH INTERVAL TO: "  + Settings.getPeasantsHealthInterval()[1]);
        System.out.println("PEASANTS POWER INTERVAL FROM: " + Settings.getPeasantsPowerInterval()[0]);
        System.out.println("PEASANTS POWER INTERVAL TO: " + Settings.getPeasantsPowerInterval()[1]);

     /*  Kingdom kingdom1 = new Kingdom("FarAway");
        King richard = (King) kingdom1.createPerson("Richard", "King", null);*/
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

        for (int i = 0; i <Settings.getKingCureCountCount(); i++){
            kingdom1.getFirstWizard().cureKing(kingdom1);
        }
        System.out.println(kingdom1);

        Knight[] pair = kingdom1.chooseRandomPair();
        kingdom1.doFight(pair);

    }

}
