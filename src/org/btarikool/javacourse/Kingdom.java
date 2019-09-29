// Kingdom.java
package org.btarikool.javacourse;

import java.util.Arrays;

public class Kingdom {
    public Person[] persons;

    public Kingdom() {
        persons = new Person[0];
    }

    public void doActionsUp() {
        try {
            for (int i = persons.length - 1; i >= 0; i--) {
                if (persons[i].getTitle() == "King ") {
                    King king = (King) persons[i];
                    king.givePeasantToLord(i);
                    continue;
                }
                persons[i].doAction("STUB", true);
            }
        } catch (Exception e) {
            System.out.println("Action up gone wrong");
        }
    }

    public void doActionsDown() {
        try {
        for(int i = 0; i < persons.length; i++) {
            if (persons[i].getTitle() == "King ") {
                continue;
            }
            persons[i].doAction("STUB");

        }
        } catch (Exception e) {
            System.out.println("Action down gone wrong");
        }
    }

    public Person createPerson(String title, String name, Person chief) {
        Person person;
        switch (title.toLowerCase()) {
            case "king":
                person = new King(name, chief, this);
                break;
            case "lord":
                person = new Lord(name, chief, this);
                break;
            case "knight":
                person = new Knight(name, chief, this);
                break;
            default:
                person = new Peasant(name, chief, this);
                break;
        }
        int lastIndex = persons.length;
        Person[] addedPersons = Arrays.copyOf(persons, lastIndex + 1);
        addedPersons[lastIndex] = person;
        this.persons = addedPersons;
        return person;
    }

    public static void report(King king, Lord lord1, Lord lord2, Knight knight1, Knight knight2) {
        king.report();
        lord1.report();
        lord2.report();
        knight1.report();
        knight2.report();
    }

    @Override
    public String toString() {
        String ret = "";
        for (Person p : this.persons) {
            ret += p.toString() + "\n";
        }
        return ret;
    }
}
