// Kingdom.java
package org.btarikool.javacourse;

public class Game {

    public static void main(String[] args) {
        Kingdom kingdom1 = new Kingdom();
        King king = (King) kingdom1.createPerson("king","Richard", null);
        Lord lord1 = (Lord) kingdom1.createPerson("lord", "Lee", king);
        Lord lord2 = (Lord) kingdom1.createPerson("lord","Lionel", king);
        Knight knight1 = (Knight) kingdom1.createPerson("knight", "Koosey", lord1);
        Knight knight2 = (Knight) kingdom1.createPerson("knight", "Keeney", lord2);
        System.out.println(kingdom1.toString());
        kingdom1.doActionsUp();
        kingdom1.doActionsDown();
    }

    public static boolean runActionsChain(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {
        try {
            knight1.doAction("my hommage to " + lord1.getTitleAndName(), true);
            knight2.doAction("my military service to " + lord2.getTitleAndName(), true);
            lord1.doAction("my loyalty to " + king.getTitleAndName(), true);
            lord2.doAction("my military aid to " + king.getTitleAndName(), true);
            king.doAction("I give fief to " + lord1.getTitleAndName());
            king.doAction("I give 2 peasants to " + lord2.getTitleAndName());
//            Peasant peasant1 = king.providePeasant(lord1);
//            Peasant peasant2 = king.providePeasant(lord1);
            lord1.doAction("I give food to " + knight1.getTitleAndName());
            lord2.doAction("I give protection to " + knight2.getTitleAndName());
            knight1.doAction("I bring new lands to " + king.getTitleAndName(), true);
            knight2.doAction("I bring new lands to " + king.getTitleAndName(), true);
//            peasant1.doAction("I give food to " + lord1.getTitleAndName(), true);
//            peasant2.doAction("I give food to " + lord2.getTitleAndName(), true);
//            peasant1.report();
//            peasant2.report();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void report(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {
        king.report();
        lord1.report();
        lord2.report();
        knight1.report();
        knight2.report();
    }
}
