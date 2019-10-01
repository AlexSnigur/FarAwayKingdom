package org.btarikool.javacourse;

public class Action {
    public void doAction(Person p, Person subordinate) {
        int upIndex = subordinate == null ? 0 : 1;
        int evenIndex = p.id % 2 == 1 ? 0 : 1;
        Person targetPerson = subordinate != null ? subordinate : p.chief;
        String actionContent = p.actions[upIndex][evenIndex];
        System.out.println(p.getTitleAndName() + " gives " + actionContent + " to " + targetPerson.getTitleAndName());
    }
}
