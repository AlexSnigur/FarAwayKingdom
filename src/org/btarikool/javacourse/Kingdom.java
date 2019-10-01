// Kingdom.java
package org.btarikool.javacourse;

import java.util.Arrays;

public class Kingdom {
    private String name;
    Person[] people = {};
    public Kingdom(String name) {
        this.name = name;
    }

    public Person createPerson(String name, String title, Person chief){
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
            default:
                createdPerson = new Peasant(name);

        }
        createdPerson.setChief(chief);
        addToPeople(createdPerson);
        return createdPerson;
    }

    private void addToPeople(Person p) {
        int arrayLen = this.people.length;
        Person [] newPeopleArray = Arrays.copyOf(this.people, arrayLen + 1);
        newPeopleArray[arrayLen] = p;
        this.people = newPeopleArray;
    }

    public void runActions() {
        int lastIndex = this.people.length - 1;
        for(int i = lastIndex; i >= 0; i-- ) {
            System.out.println(i + " : " + this.people[i]);
        }
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

    @Override
    public String toString() {
        String kingdom = "";
        for (Person p : this.people) {
            kingdom = kingdom + p + "; \n";
        }
        return kingdom;
    }

}
