package org.btarikool.javacourse;

public class Knight extends Human {
    private final static String TITLE = "Knight ";
    private final double HEALTH = 0.7d;
    private final int AUTHORITY = 5;
    private final int STATUS_LEVEL = 2;

    public Knight(String name){
        super(name, TITLE, 0.7d, 5, 2);
        System.out.println(this.toString());
    }

    public void oath(Human person) {
        System.out.println(getTitle() + getName() + " swears to the " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public  void defend(Human person) {
        System.out.println(getTitle() + getName() + " defends " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void bringsNewLand(Human person, String present) {
        System.out.println(getTitle() + getName() + " conquering " + present + " " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }
}