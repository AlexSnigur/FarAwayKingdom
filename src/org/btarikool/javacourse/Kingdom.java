// Kingdom.java
package org.btarikool.javacourse;

public class Kingdom {

    public static void main(String[] args) {
        System.out.println("Kingdom of Far Far Away");
        if (args.length < 5) {
            System.out.println("please pass 5 names as parameters to start ");
            return;
        }
        Knight knight1 = new Knight(args[3]);
        Knight knight2 = new Knight(args[4]);
        Lord lord1 = new Lord(args[2]);
        Lord lord2 = new Lord(args[1]);
        King king = new King(args[0]);

        Person.IS_FIRST_ITERATION = true;
        try {
            Enemy kingOfNight = new Enemy("King of Night", "We will ", new StringBuffer());
            kingOfNight.doAction("King of Night says:" + kingOfNight.phrase.toString());
            Enemy knightOfNight = kingOfNight.createEnemy("Knight of Night", "take over ");
            knightOfNight.doAction("Knight of Night says" + knightOfNight.phrase.toString());
            Enemy whiteWalker = knightOfNight.createEnemy("White Walker", "the world!");
            whiteWalker.doAction("White Walker says" + whiteWalker.phrase.toString());

            System.out.println("Encrypted phrase: "+whiteWalker.getEncryptedPhrase());
            System.out.println("Decrypted phrase: "+whiteWalker.getDecryptedPhrase());

            step(king, lord1, lord2, knight1, knight2, 1);
            Person.IS_FIRST_ITERATION = false;
            step(king, lord1, lord2, knight1, knight2, 2);
            step(king, lord1, lord2, knight1, knight2, 3);
            step(king, lord1, lord2, knight1, knight2, 4);
            step(king, lord1, lord2, knight1, knight2, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Person.printDeadPeople();
    }

    public static void step(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2, int stepCount) throws Exception {
        System.out.println(" ***** " + stepCount + " ***** ");
        if (runActionsChain(king, lord1, lord2, knight1, knight2) == false) {
            throw new Exception("GAME OVER!");
        }
//        System.out.println("REPORT" + stepCount);
//        report(king, lord1, lord2, knight1, knight2);
    }

    public static boolean runActionsChain(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {
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
