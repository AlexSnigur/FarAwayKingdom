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
        System.out.println(" ***** 1 ***** ");

        if (runActionsChain(king, lord1, lord2, knight1, knight2) == false) {
            System.out.println("GAME OVER!");
            return;
        }
        System.out.println("REPORT 1");
        report(king, lord1, lord2, knight1, knight2);
        Person.runCounter++;

        if (runActionsChain(king, lord1, lord2, knight1, knight2) == false) {
            System.out.println("GAME OVER!");
            return;
        }
        System.out.println("REPORT 2");
        report(king, lord1, lord2, knight1, knight2);
        Person.runCounter++;


        if (runActionsChain(king, lord1, lord2, knight1, knight2) == false) {
            System.out.println("GAME OVER!");
            return;
        }
        System.out.println("REPORT 3");
        report(king, lord1, lord2, knight1, knight2);
        Person.runCounter++;


        if (runActionsChain(king, lord1, lord2, knight1, knight2) == false) {
            System.out.println("GAME OVER!");
            return;
        }
        System.out.println("REPORT 4");
        report(king, lord1, lord2, knight1, knight2);
        Person.runCounter++;

        if (runActionsChain(king, lord1, lord2, knight1, knight2) == false) {
            System.out.println("GAME OVER!");
            return;
        }
        System.out.println("REPORT 5");
        report(king, lord1, lord2, knight1, knight2);
        Person.runCounter++;



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
            peasant1.report();
            peasant2.report();

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
