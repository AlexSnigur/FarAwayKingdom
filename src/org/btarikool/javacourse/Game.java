package org.btarikool.javacourse;

import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        Kingdom kingdom = new Kingdom("Far Far Away Kingdom");
        King king = (King)kingdom.createHuman("Artur", "king", null);
        Wizard wizard = (Wizard)kingdom.createHuman("Marina", "wizard", king);

        for (int x = 0; x < 8; x++) wizard.doToHeal(king);
        while (kingdom.getKnightList().size() > 1) kingdom.doFightRand(kingdom.getRandomPair());
        System.out.println("Our champion is: " + kingdom.getKnightList().get(0));

    }
/*    public static void runActions(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {

    }*/

}
