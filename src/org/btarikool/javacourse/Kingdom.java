// Kingdom.java
package org.btarikool.javacourse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kingdom {
    private String name;
    Person[] people = {};

    public Kingdom(String name) {
        this.name = name;
    }

    public Person createPerson(String name, String title, Person chief) {
        Person createdPerson;
        switch (title.toLowerCase()) {
            case "king":
                createdPerson = new King(name);
                break;
            case "lord":
                createdPerson = new Lord(name);
                break;
            case "knight":
                createdPerson = new Knight(name);
                break;
            case "wizard":
                createdPerson = new Wizard(name);
                break;
            default:
                createdPerson = new Peasant(name);

        }
        createdPerson.setChief(chief);
        if (chief != null) {
            chief.addSubordinate(createdPerson);
        }
        addToPeople(createdPerson);
        return createdPerson;
    }


    private void addToPeople(Person p) {
        p.setId(this.people.length);
        int arrayLen = this.people.length;
        Person[] newPeopleArray = Arrays.copyOf(this.people, arrayLen + 1);
        newPeopleArray[arrayLen] = p;
        this.people = newPeopleArray;
    }

    public void runActionsUp() {
        int lastIndex = this.people.length - 1;
        for (int i = lastIndex; i >= 0; i--) {
            if (this.people[i] instanceof King) {
                ((King) this.people[i]).providePeasantToSubordinates(this);
                continue;
            }
            this.people[i].doAction();

        }
    }

    public void runActionsDown() {
        for (Person p : this.people) {
            if (p.subordinates == null || p.subordinates.length == 0) {
                continue;
            }
            for (Person subordinate : p.subordinates) {
                p.doAction(subordinate);
            }
        }
    }

    public Knight[] chooseRandomPair() {
        List<Person> allKnights = new ArrayList<>();
        for (Person p : this.people) {
            if (p instanceof Knight
                    && !p.isDead)
                   {
                allKnights.add(p);
            }
        }
        int knightLen = allKnights.size();
        if (knightLen < 2) {
            return null;
        }
        int firstIndex = (int) (Math.random() * knightLen);
        Knight kn1 = (Knight) allKnights.get(firstIndex);
        int secIndex;
        do {
            secIndex = (int) (Math.random() * knightLen);
        } while (firstIndex == secIndex);
        Knight kn2 = (Knight) allKnights.get(secIndex);
        return new Knight[]{kn1, kn2};
    }

    public static void doFight(Knight[] pair) {
        Knight winner;
        Knight loser;
        if (pair == null) {
            System.out.println("Invalid pair of fighters!");
        }
        System.out.println("Knights will fight: d");
        System.out.println(pair[0] + "\n" + pair[1]);
        double delta = pair[0].getRank() - pair[1].getRank();
        if (Math.abs(delta) > 0.5) {
            winner = delta > 0 ? pair[0] : pair[1];
            loser = delta > 0 ? pair[1] : pair[0];
        } else {
            winner = pair[0].power > pair[1].power ? pair[0] : pair[1];
            loser = pair[0].power > pair[1].power ? pair[1] : pair[0];
        }
        updateKnights(winner, loser);
        System.out.println("Winner is: " + winner);
        System.out.println("Loser is: " + loser);

    }

    private static void updateKnights(Knight winner, Knight loser) {
        winner.setHealth(winner.getHealth() - loser.getHealth() / 2);
        winner.power += loser.power / 2;
        loser.setHealth(0);
        loser.isDead = true;

    }

    public void printSubordinates(Person[] persons, int level, PrintWriter writer) {
        if (persons == null) {
            return;
        }
        for (Person p : persons) {
            String tabs = p.isDead ? "☠" : "";
            if (level == 0 && p.chief != null) {
                continue;
            } else {
                for (int i = 0; i < level; i++) {
                    tabs += "\t";
                }
            }
            writer.println(tabs + p);
            printSubordinates(p.subordinates, level + 1, writer);
        }
    }

    public void saveKingdomState() {
        try {
            FileWriter writer = new FileWriter("kingdom_out.log");
            PrintWriter printWriter = new PrintWriter(writer);
            printSubordinates(this.people, 0, printWriter);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Wizard getFirstWizard() {
        for (Person p : this.people) {
            if (p instanceof Wizard) {
                return (Wizard) p;
            }
        }
        return null;
    }



    @Override
    public String toString() {
        String kingdom = "***" +  this.name + " Kingdom *** \n";
        for (Person p : this.people) {
            kingdom = kingdom + p + "; \n";
        }
        return kingdom;
    }
}
