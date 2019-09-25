package org.btarikool.javacourse;

public class Lord extends Human {
    private final static String TITLE = "Lord ";
    private final double HEALTH = 0.7d;
    private final int AUTHORITY = 5;
    private final int STATUS_LEVEL = 2;

    public Lord(String name) {
        super(name, TITLE, 0.7d, 5, 2);
        System.out.println(this.toString());
    }

    public void reverence(Human person) {
        System.out.println(getTitle() + getName() + " gives homage " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void defend(Human person) {
        System.out.println(getTitle() + getName() + " defends " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void presents(Human person, String present) {
        System.out.println(getTitle() + getName() + " gives " + present + " " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }
}