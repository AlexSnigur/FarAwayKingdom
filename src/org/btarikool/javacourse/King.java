package org.btarikool.javacourse;

public class King extends Human {

    public King(String name){
        super(name);
        System.out.println("Я - король, меня зовут " + getName() + "!");
    }

    public void present(Human person, String present) {
        System.out.println("Король " + getName() + " даёт " + present + " " + person);
    }

    @Override
    public String toString() {
        return "король " + getName() + ".";
    }
}
