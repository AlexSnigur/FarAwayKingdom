package org.btarikool.javacourse;

public class Lord extends Human{

    public Lord(String name){
        super(name);
        System.out.println("Я - лорд, меня зовут " + getName() + "!");
    }

    public void reverence(Human person) {
        System.out.println("Лорд " + getName() + " почитает " + person);
    }

    public void defend(Human person) {
        System.out.println("Лорд " + getName() + " защищает " + person);
    }

    @Override
    public String toString() {
        return "лорд " + getName() + ".";
    }
}
