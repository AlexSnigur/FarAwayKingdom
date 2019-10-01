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
        if (chief != null){
            chief.addSubordinate(createdPerson);
        }
        addToPeople(createdPerson);
        return createdPerson;
    }

    private void addToPeople(Person p) {
        p.setId(this.people.length);
        int arrayLen = this.people.length;
        Person [] newPeopleArray = Arrays.copyOf(this.people, arrayLen + 1);
        newPeopleArray[arrayLen] = p;
        this.people = newPeopleArray;
    }

    public void runActionsUp() {
        int lastIndex = this.people.length - 1;
        for(int i = lastIndex; i >= 0; i-- ) {
            if (this.people[i] instanceof King) {
                ((King) this.people[i]).providePeasantToSubordinates(this);
                continue;
            }
            this.people[i].doAction ();

        }
    }

    public void runActionsDown() {
        for(Person p: this.people) {
            if (p.subordinates == null || p.subordinates.length == 0) {
                continue;
            }
            for (Person subordinate: p.subordinates) {
                p.doAction(subordinate);
            }


        }
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
