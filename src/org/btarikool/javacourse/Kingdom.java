package org.btarikool.javacourse;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Kingdom {


    public static void main(String[] args) {

        if (args.length < 5) {
            System.out.println("Provide 5 names as arguments");
            return;
        }
        //create the King, two Lords and two Knights
        King theKing = new King(args[0]); //name - args[0] (e.g. "Arthur")
        Lord lordOne = new Lord(args[1]); //name - args[1]
        Lord lordTwo = new Lord(args[2]); //name - args[2]
        Knight knightOne = new Knight(args[3]); //name - args[3]
        Knight knightTwo = new Knight(args[4]); //name - args[4]

        //add white space
        System.out.println("\n");

        //main actions
        System.out.println("--------------------");
        doActions(theKing, lordOne, lordTwo, knightOne, knightTwo);
        System.out.println("\n");

        //check status
        System.out.println("Check Kingdom status");
        report(theKing, lordOne, lordTwo, knightOne, knightTwo);
        System.out.println("\n");

        //coefficient to reach King 20 power without any deaths
        Hooman.changeHpLoss(0.6);

        //loop to reach King's 20 power
        while ((theKing.power<20)) {
            System.out.println("--------------------");
            doActions(theKing, lordOne, lordTwo, knightOne, knightTwo);
            System.out.println("\n");
            //check status
            System.out.println("Check Kingdom status");
            report(theKing, lordOne, lordTwo, knightOne, knightTwo);
            System.out.println("\n");
        }

        System.out.println("Deadlist: ");
        Hooman.getDeadList();
    }

    public static boolean statusCheck() {
        for (Hooman check : Hooman.getHoomans()) {
            if (check.getHealth() < 0.2) {
                Hooman.addToDeadList(check);
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
        theKing.giveLand(lordOne);
        theKing.givePeasants(lordTwo);
        theKing.givePeasants(lordTwo);
        Peasant peasantOne = new Peasant("number #1");
        Peasant peasantTwo = new Peasant("number #2");
        System.out.println(lordOne.getNameAndTitle() + lordOne.giveFood(knightOne));
        System.out.println(lordTwo.getNameAndTitle() + lordTwo.protect(knightTwo) + knightTwo.getNameAndTitle());
        System.out.println(knightOne.getNameAndTitle() + knightOne.conquerLand(theKing) + "King " + theKing.getNameAndTitle());
        System.out.println(knightTwo.getNameAndTitle() + knightTwo.conquerLand(theKing) + "King " + theKing.getNameAndTitle());
        System.out.println(peasantOne.getNameAndTitle() + peasantOne.giveFood(lordOne) + lordOne.getNameAndTitle());
        System.out.println(peasantTwo.getNameAndTitle() + peasantTwo.giveFood(lordTwo) + lordTwo.getNameAndTitle());
        statusCheck();
        /*if (statusCheck()){
            System.out.println("GAME OVER!!!!!");
        }*/


    }
}

