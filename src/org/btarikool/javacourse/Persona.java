package org.btarikool.javacourse;

public class Persona {
    private String name;

    public Persona(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void say(String message) {
        System.out.println(message);
    }
}
