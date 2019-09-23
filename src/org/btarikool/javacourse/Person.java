package org.btarikool.javacourse;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void perform(String action) {
        System.out.println(action);
    }
}
