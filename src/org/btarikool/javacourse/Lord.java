package org.btarikool.javacourse;

public class Lord extends Human {
    private final static String TITLE = "Lord ";
    private final double HEALTH = 0.7d;
    private final int AUTHORITY = 5;
    private final int STATUS_LEVEL = 2;

    public Lord(String name) {
        super(name);
        this.healPoints = HEALTH;
        this.title = TITLE;
        this.authorityPoints = AUTHORITY;
        this.statusLevel = STATUS_LEVEL;
        this.rank = HEALTH * AUTHORITY;
        System.out.println(this.toString());
    }

    public void reverence(Human person) {
        System.out.println(TITLE + getName() + " gives homage " + person.title + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void defend(Human person) {
        System.out.println(TITLE + getName() + " defends " + person.title + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void presents(Human person, String present) {
        System.out.println(TITLE + getName() + " gives " + present + " " + person.title + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }
}