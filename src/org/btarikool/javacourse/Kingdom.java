package org.btarikool.javacourse;

// Program arguments:
// "Robert Baratheon" "Eddard Stark" "Petyr Baelish" "Rodrik Cassel" "Jorah Mormont"

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    private String name;
    private final List<Human> HUMAN_LIST = new ArrayList<>();
    private final List<Human> KING_LIST = new ArrayList<>();
    private final List<Human> LORD_LIST = new ArrayList<>();
    private final List<Human> KNIGHT_LIST = new ArrayList<>();
    private final List<Human> PEASANT_LIST = new ArrayList<>();
    private final List<Human> ENEMY_LIST = new ArrayList<>();
    private final List<Human> DEAD_LIST = new ArrayList<>();

    public Kingdom() {
    }

    public Kingdom(String name) {
        this.name = name;
    }

    public Human createHuman(String name, String title) throws IOException {
        Human human = null;
        switch (title.toLowerCase()) {
            case "king":
                human = new King(name, HUMAN_LIST.size(), KING_LIST.size(), this);
                addToLists(human);
                break;
            case "lord":
                human = new Lord(name, HUMAN_LIST.size(), LORD_LIST.size(), this);
                addToLists(human);
                break;
            case "knight":
                human = new Knight(name, HUMAN_LIST.size(), KNIGHT_LIST.size(), this);
                addToLists(human);
                break;
            case "enemy":
                human = new Enemy(name, HUMAN_LIST.size(), ENEMY_LIST.size(), this);
                addToLists(human);
                break;
        }
        return human;
    }

    public List<Human> getHumanList() {
        return HUMAN_LIST;
    }

    public List<Human> getKingList() {
        return KING_LIST;
    }

    public List<Human> getLordList() {
        return LORD_LIST;
    }

    public List<Human> getKnightList() {
        return KNIGHT_LIST;
    }

    public List<Human> getPeasantList() {
        return PEASANT_LIST;
    }

    public List<Human> getEnemyList() {
        return ENEMY_LIST;
    }

    public List<Human> getDeadList() {
        return DEAD_LIST;
    }

    public void printHumanList() {
        HUMAN_LIST.stream().forEach(System.out::println);
    }

    public void addToLists(Human human) {
        HUMAN_LIST.add(human);
        if (human instanceof King) KING_LIST.add(human);
        else if (human instanceof Lord) LORD_LIST.add(human);
        else if (human instanceof Knight) KNIGHT_LIST.add(human);
        else if (human instanceof Peasant) PEASANT_LIST.add(human);
        else if (human instanceof Enemy) ENEMY_LIST.add(human);
    }

    public void removeFromAliveSetToDeadList(Human human) {
        if (human.getHealPoints() < Human.getMINIMUM_HP_LEVEL()) {
            human.setStatus(false);
            DEAD_LIST.add(human);
            HUMAN_LIST.set(human.getCollectiveListId(), null);
            if (human instanceof King) KING_LIST.set(human.getOwnListId(), null);
            else if (human instanceof Lord) LORD_LIST.set(human.getOwnListId(), null);
            else if (human instanceof Knight) KNIGHT_LIST.set(human.getOwnListId(), null);
            else if (human instanceof Peasant) PEASANT_LIST.set(human.getOwnListId(), null);
            else if (human instanceof Enemy) ENEMY_LIST.set(human.getOwnListId(), null);
        }
    }


/*        //Iterate actions 1st time
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
        }*/
}