// King.java
package org.btarikool.javacourse;

public abstract class Person {
    String name;
    String title;
    double health;
    int power;
    private static final String TITLE = "";



    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public String getTitleAndName()  {
        return title + name;
    }

    public void doAction(String actionContent, boolean isUpwards) throws Exception {
        Action action = new Action();
        if (isUpwards) {
            health *=  1.33;
            power--;
        } else {
            health /= 1.164993;
            power++;
        }
        if (health < 0.2) {
            throw new Exception("Game over!");
        }
        action.doAction(this.getTitleAndName(), actionContent);
    }
    public void doAction(String actionContent) throws Exception{
        doAction(actionContent, false);
    }

    public void report() {
        System.out.println(getTitleAndName() + "'s health: " + health + "; power: " + power);
    }

    public void sayHello(String name) {
        System.out.println("Hello! I am the " + getTitleAndName() );

    }


}