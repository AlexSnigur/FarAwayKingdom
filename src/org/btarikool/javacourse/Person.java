// King.java
package org.btarikool.javacourse;

public abstract class Person {
    String name;
    String title;
    double health;
    int power;
    private static final String TITLE = "";
    private static double COEFFICIENT_UP = 1.33;
    private static double COEFFICIENT_DOWN = 1.164993;
    private static final double COEFFICIENT = 0.8;
    public static boolean IS_FIRST_ITERATION = true;
    public static double LOWER_HEALTH_BOUNDARY = 0.2;

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
            COEFFICIENT_UP /= COEFFICIENT;
            COEFFICIENT_DOWN *= COEFFICIENT;
        }
        Action action = new Action();
        if (isUpwards) {
            health *= COEFFICIENT_UP;
            power--;
        } else {
            health /= COEFFICIENT_DOWN;
            power++;
        }
        if (health < LOWER_HEALTH_BOUNDARY) {
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

    public static double getRank(double power, double health) {
        return power * health;
    }

}