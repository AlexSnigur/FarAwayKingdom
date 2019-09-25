package org.btarikool.javacourse;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    private static List<Hooman> hoomans = new ArrayList<>();

    public static void main(String[] args) {

        if (args.length < 5) {
            System.out.println("Provide 5 names as arguments");
            return;
        }
        //create the King, two Lords and two Knights
        King theKing = new King(args[0], "King"); //name - args[0] (e.g. "Arthur")
        Lord lordOne = new Lord(args[1], "Lord"); //name - args[1]
        Lord lordTwo = new Lord(args[2], "Lord"); //name - args[2]
        Knight knightOne = new Knight(args[3], "Knight"); //name - args[3]
        Knight knightTwo = new Knight(args[4], "Knight"); //name - args[4]
        hoomans.add(theKing);
        hoomans.add(lordOne);
        hoomans.add(lordTwo);
        hoomans.add(knightOne);
        hoomans.add(knightTwo);


        //add white space
        System.out.println("\n");

        //main actions
        System.out.println("--------------------");
        //main actions
        doActions(theKing, lordOne, lordTwo, knightOne, knightTwo);
        System.out.println("\n");
        //check status
        System.out.println("Check Kingdom status");
        report(theKing, lordOne, lordTwo, knightOne, knightTwo);
        System.out.println("\n");

        Hooman.changeHpLoss(0.7);

        while (!statusCheck() && (theKing.power<20)) {

            System.out.println("--------------------");
            //main actions
            doActions(theKing, lordOne, lordTwo, knightOne, knightTwo);
            System.out.println("\n");
            //check status
            System.out.println("Check Kingdom status");
            report(theKing, lordOne, lordTwo, knightOne, knightTwo);
            System.out.println("\n");




        }
    }

    public static boolean statusCheck() {
        for (Hooman check : hoomans) {
            if (check.getHealth() < 0.2) {
                return true;
            }
        }
        return false;
    }

    public static void report(King theKing, Lord lordOne, Lord lordTwo, Knight knightOne, Knight knightTwo) {
        theKing.status();
        lordOne.status();
        lordTwo.status();
        knightOne.status();
        knightTwo.status();

    }

    public static void doActions(King theKing, Lord lordOne, Lord lordTwo, Knight knightOne, Knight knightTwo) {
        System.out.println(knightOne.getNameAndTitle() + ": " + knightOne.greetings(lordOne) + lordOne.getNameAndTitle());
        System.out.println(knightTwo.getNameAndTitle() + knightTwo.protect(lordTwo) + lordTwo.getNameAndTitle());
        System.out.println(lordOne.getNameAndTitle() + ": " + lordOne.greetings(theKing) + "King " + theKing.getNameAndTitle());
        System.out.println(lordTwo.getNameAndTitle() + lordTwo.protect(theKing) + "King " + theKing.getNameAndTitle());
        System.out.println(theKing.giveLand(lordOne));
        theKing.givePeasants(lordTwo);
        theKing.givePeasants(lordTwo);
        Peasant peasantOne = new Peasant("number #1", "Peasant");
        Peasant peasantTwo = new Peasant("number #2", "Peasant");
        System.out.println(lordOne.getNameAndTitle() + lordOne.giveFood(knightOne));
        System.out.println(lordTwo.getNameAndTitle() + lordTwo.protect(knightTwo) + knightTwo.getNameAndTitle());
        System.out.println(knightOne.getNameAndTitle() + knightOne.conquerLand(theKing) + "King " + theKing.getNameAndTitle());
        System.out.println(knightTwo.getNameAndTitle() + knightTwo.conquerLand(theKing) + "King " + theKing.getNameAndTitle());
        System.out.println(peasantOne.getNameAndTitle() + peasantOne.giveFood(lordOne) + lordOne.getNameAndTitle());
        System.out.println(peasantTwo.getNameAndTitle() + peasantTwo.giveFood(lordTwo) + lordTwo.getNameAndTitle());
        if (statusCheck())
            System.out.println("GAME OVER!!!!!");
    }
}

