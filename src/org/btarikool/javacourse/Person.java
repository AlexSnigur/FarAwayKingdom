// King.java
package org.btarikool.javacourse;

public abstract class Person {
    String name;
    String title;
    double health;
    int power;
    private static final String TITLE = "";
    private static double COEFICIENT_UP = 1.33;
    private static double COEFICIENT_DOWN = 1.164993;
    private static final double COEFICIENT = 0.8;
    public static boolean IS_FIRST_ITERATION = true;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getTitleAndName() {
        return title + name;
    }

    public void doAction(String actionContent, boolean isUpwards) throws Exception {
        if (!IS_FIRST_ITERATION) {
            COEFICIENT_UP /= COEFICIENT;
            COEFICIENT_DOWN *= COEFICIENT;
        }
        Action action = new Action();
        if (isUpwards) {
            health *= COEFICIENT_UP;
            power--;
        } else {
            health /= COEFICIENT_DOWN;
            power++;
        }
        if (health < 0.2) {
            throw new Exception("Game over!");
        }
        action.doAction(this.getTitleAndName(), actionContent);
    }

    public void doAction(String actionContent) throws Exception {
        doAction(actionContent, false);
    }

    public void report() {
        System.out.println(getTitleAndName() + "'s health: " + health + "; power: " + power);
    }

    public void sayHello(String name) {
        System.out.println("Hello! I am the " + getTitleAndName());

    }


}