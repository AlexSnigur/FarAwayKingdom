package org.btarikool.javacourse;

public class King extends Human {
    private final static String TITLE = "King ";
    private final double HEALTH = 0.5d;
    private final int AUTHORITY = 10;
    private final int STATUS_LEVEL = 1;
    private int numOfPeasants = 10;

    public King(String name){
        super(name);
        this.healPoints = HEALTH;
        this.title = TITLE;
        this.authorityPoints = AUTHORITY;
        this.statusLevel = STATUS_LEVEL;
        System.out.println(this.toString());
    }

    public void presents(Human person, String present) {
        System.out.println(TITLE + getName() + " gives " + present + " " + person.title + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void presentsPeasant(Human person) {
        Peasant peasant = new Peasant(person.title + person.getName());
        System.out.println(TITLE + getName() + " gives " + peasant.getName());
        this.numOfPeasants--;
    }
}