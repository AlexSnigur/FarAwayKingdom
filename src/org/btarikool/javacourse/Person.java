// King.java
package org.btarikool.javacourse;

public abstract class Person {
    String name;
    String title;
    double health;
    int power;
    private static final double HEALTH_CHANGE_COEFFICIENT = 0.98;
    public static int runCounter = 0;
    private double healthUpCoeff = 1.33;
    private double healthDownCoeff = 1.164993;





    public Person(String name) {
        this.name = name;
    }

    public String getTitleAndName()  {
        return title + name;
    }

    public void doAction(String actionContent, boolean isUpwards) throws Exception {
        Action action = new Action();
        if (Person.runCounter > 0) {
            healthUpCoeff /= HEALTH_CHANGE_COEFFICIENT;
            healthDownCoeff *= HEALTH_CHANGE_COEFFICIENT;
        }
        if (isUpwards) {
            health *= healthUpCoeff;
            power--;
        } else {
            health /= healthDownCoeff;
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
}


