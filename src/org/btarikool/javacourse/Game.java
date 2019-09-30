// Kingdom.java
package org.btarikool.javacourse;

public class Game {

    public static void main(String[] args) {

        Kingdom kingdom1 = new Kingdom("FarAway");
        King richard = (King) kingdom1.createPerson("Richard", "King" );
        System.out.println(richard);
        Lord lionel = (Lord) kingdom1.createPerson("Lionel", "Lord");
        System.out.println(lionel);
        Lord lee = (Lord) kingdom1.createPerson("Lee", "Lord");
        System.out.println(lee);
        Knight keeney = (Knight) kingdom1.createPerson("Keeney", "kNIGHT");
        System.out.println(keeney);
        Knight koosey = (Knight) kingdom1.createPerson("koosey", "knight");
        System.out.println(koosey);


    }

    public static boolean runActionsChain(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {
/*
        try {
            knight1.doAction("my hommage to " + lord1.getTitleAndName(), true);
            knight2.doAction("my military service to " + lord2.getTitleAndName(), true);
            lord1.doAction("my loyalty to " + king.getTitleAndName(), true);
            lord2.doAction("my military aid to " + king.getTitleAndName(), true);
            king.doAction("I give fief to " + lord1.getTitleAndName());
            king.doAction("I give 2 peasants to " + lord2.getTitleAndName());
            Peasant peasant1 = king.providePeasant(lord1.getTitleAndName());
            Peasant peasant2 = king.providePeasant(lord1.getTitleAndName());
            lord1.doAction("I give food to " + knight1.getTitleAndName());
            lord2.doAction("I give protection to " + knight2.getTitleAndName());
            knight1.doAction("I bring new lands to " + king.getTitleAndName(), true);
            knight2.doAction("I bring new lands to " + king.getTitleAndName(), true);
            peasant1.doAction("I give food to " + lord1.getTitleAndName(), true);
            peasant2.doAction("I give food to " + lord2.getTitleAndName(), true);
            peasant1.report();
            peasant2.report();

        } catch (Exception e) {
            return false;
        }
        */
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
