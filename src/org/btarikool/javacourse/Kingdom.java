package org.btarikool.javacourse;

// Program arguments:
// "Robert Baratheon" "Eddard Stark" "Petyr Baelish" "Rodrik Cassel" "Jorah Mormont"

public class Kingdom {

    public static void main(String[] args) throws Exception {

        //Creating objects
        King king = new King(args[0]);
        Lord lordFirst = new Lord(args[1]);
        Lord lordSecond = new Lord(args[2]);
        Knight knightFirst = new Knight(args[3]);
        Knight knightSecond = new Knight(args[4]);
        System.out.println();

        //Iterate actions 1st time
        iterationOfActions(knightFirst, knightSecond, lordFirst, lordSecond, king);
        System.out.println();

        //Heroes stats after 1st iteration
        Human.printListOfHumans();
        Human.printListOfPeasants();
        System.out.println();

        //HealPoint's index change by coefficient (insert ur value)
        Human.changeHpIndex(0.4);

        //Iteration using for cycle
        while (king.getAuthorityPoints() < 20) {
            iterationOfActions(knightFirst, knightSecond, lordFirst, lordSecond, king);
            System.out.println();
        }

        //Heroes stats after doing some iterations
        System.out.println();
        Human.printListOfHumans();
        Human.printListOfPeasants();
    }

    public static void iterationOfActions(Knight knightFirst, Knight knightSecond, Lord lordFirst, Lord lordSecond, King king) throws Exception {
        knightFirst.oath(lordFirst);
        knightSecond.defend(lordSecond);
        lordFirst.reverence(king);
        lordSecond.defend(king);
        king.presents(lordFirst, "the lends");
        king.presentsPeasant(lordSecond);
        king.presentsPeasant(lordSecond);
        lordFirst.presents(knightFirst, "1 food");
        lordSecond.presents(knightSecond, "protection");
        knightFirst.bringsNewLand(king, "new lend");
        knightSecond.bringsNewLand(king, "new lend");
        Human.getPeasantsList().get(1).presents(lordFirst, "food");
        Human.getPeasantsList().get(0).presents(lordSecond, "food");

        //Controls if any Human or Peasant object has HealPoint level under 0.2.
        //If any of this conditions equals true, program ends throwing Game Over exception.
        for (Human x : Human.getHumansList()) {
            for (Peasant y : Human.getPeasantsList()){
                if (x.getHealPoints() < 0.2 || y.getHealPoints() < 0.2) {
                    System.out.println("\nGAME IS OVER! " + (x.getHealPoints()>y.getHealPoints()?y.getName():x.getName()) + "'s HealPoints level is under 0.2.");
                    Exception exception = new Exception("Game Over, one of the humans is dead.");
                    throw exception;
                }
            }
        }
    }
}