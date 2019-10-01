package org.btarikool.javacourse;

import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        Kingdom kingdom = new Kingdom("Far Far Away Kingdom");
        King king = (King)kingdom.createHuman("Artur", "king", null);
        Lord lord1 = (Lord)kingdom.createHuman("Daniel", "Lord", king);
        Lord lord2 = (Lord)kingdom.createHuman("Jack", "Lord", king);
        Knight knight1 = (Knight)kingdom.createHuman("Michael", "Knight", lord1);
        Knight knight2 = (Knight)kingdom.createHuman("Fionna", "Knight", lord2);
        runActions(king, lord1, lord2, knight1, knight2);
        System.out.println("                               RESULTS:                               ");
        kingdom.getHumanList().stream().forEach(System.out::println);
        
    }
    public static void runActions(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {

        System.out.println("                               S -> C                               ");

            Peasant peasant1 = king.createPeasant();
            Peasant peasant2 = king.createPeasant();
            king.doPresentPeasant(lord1);
            king.doPresentPeasant(lord2);
            lord1.doAction(lord1.getChief(), lord1.isEvenOrOdd()?"swears loyalty to the":"gives military aid to the");
            lord2.doAction(lord2.getChief(), lord2.isEvenOrOdd()?"swears loyalty to the":"do military service to");
            knight1.doAction(knight1.getChief(), knight1.isEvenOrOdd()?"do homage to":"gives military aid to the");
            knight2.doAction(knight1.getChief(), knight2.isEvenOrOdd()?"do homage to":"gives military aid to the");
            peasant1.doAction(peasant1.getChief(), peasant1.isEvenOrOdd()?"farms the land for":"pays rent to");
            peasant2.doAction(peasant2.getChief(), peasant2.isEvenOrOdd()?"farms the land for":"pays rent to");

        System.out.println("                               C -> S                               ");

            Peasant peasant3 = king.createPeasant();
            Peasant peasant4 = king.createPeasant();
            king.doPresentPeasant(lord1);
            king.doPresentPeasant(lord2);
            king.doAction(lord1, "presents a fief to");
            king.doAction(lord2, "presents a fief to");
            lord1.doAction(lord1.getChief(), lord1.isEvenOrOdd()?"provide shelter to":"provide protection to");
            lord2.doAction(lord1.getChief(), lord2.isEvenOrOdd()?"provide shelter to":"provide protection to");
            for (Human peasant : knight1.getChief().getMyPeasantsList()) knight1.doAction(peasant, knight1.isEvenOrOdd()?"gives food to":"provide protection to");
            for (Human peasant : knight2.getChief().getMyPeasantsList()) knight2.doAction(peasant, knight2.isEvenOrOdd()?"gives food to":"provide protection to");

    }

}
