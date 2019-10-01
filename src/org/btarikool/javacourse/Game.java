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
        runActions(kingdom, king, lord1, lord2, knight1, knight2);
        System.out.println("                               RESULTS:                               ");
        kingdom.getHumanList().stream().forEach(System.out::println);


    }
    public static void runActions(Kingdom kingdom, King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {

        System.out.println("                               S -> C                               ");

        if (kingdom.isEvenOrOdd()) {
            Peasant peasant1 = king.createPeasant();
            Peasant peasant2 = king.createPeasant();
            king.doPresentPeasant(lord1);
            king.doPresentPeasant(lord2);
            lord1.doAction(lord1.getChief(),"swears loyalty to the");
            lord2.doAction(lord2.getChief(),"swears loyalty to the");
            knight1.doAction(knight1.getChief(),"do homage to");
            knight2.doAction(knight2.getChief(),"do homage to");
            peasant1.doAction(peasant1.getChief(), "farms the land for");
            peasant2.doAction(peasant2.getChief(), "farms the land for");
        } else {
            Peasant peasant1 = king.createPeasant();
            Peasant peasant2 = king.createPeasant();
            king.doPresentPeasant(lord1);
            king.doPresentPeasant(lord2);
            lord1.doAction(lord1.getChief(),"gives military aid to the");
            lord2.doAction(lord2.getChief(),"gives military aid to the");
            knight1.doAction(knight1.getChief(),"do military service to");
            knight2.doAction(knight2.getChief(),"do military service to");
            peasant1.doAction(peasant1.getChief(), "pays rent to");
            peasant2.doAction(peasant2.getChief(), "pays rent to");
        }

        System.out.println("                               C -> S                               ");

        if (kingdom.isEvenOrOdd()) {
            Peasant peasant2 = king.createPeasant();
            Peasant peasant3 = king.createPeasant();
            king.doPresentPeasant(lord1);
            king.doPresentPeasant(lord2);
            king.doAction(lord1, "presents a fief to");
            king.doAction(lord2, "presents a fief to");
            lord1.doAction(knight1, "provide shelter to");
            lord2.doAction(knight2, "provide shelter to");
            for (Human peasant : knight1.getChief().getMyPeasantsList()) knight1.doAction(peasant, "gives food to");
            for (Human peasant : knight2.getChief().getMyPeasantsList()) knight2.doAction(peasant, "gives food to");
        } else {
            king.doPresentPeasant(lord1);
            king.doPresentPeasant(lord2);
            king.doAction(lord1, "presents a fief to");
            king.doAction(lord2, "presents a fief to");
            lord1.doAction(knight1, "provide protection to");
            lord2.doAction(knight2, "provide protection to");
            for (Human peasant : knight1.getChief().getMyPeasantsList()) knight1.doAction(peasant, "provide protection to");
            for (Human peasant : knight2.getChief().getMyPeasantsList()) knight2.doAction(peasant, "provide protection to");
        }
    }

}
