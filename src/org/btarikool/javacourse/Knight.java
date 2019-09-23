package org.btarikool.javacourse;

public class Knight extends Human {


    public Knight(String name){
        super(name);
        System.out.println("Я - рыцарь, меня зовут " + getName() + "!");
    }

    public  void oath(Human person) {
        System.out.println("Рыцарь " + getName() + " присягает " + person);
    }

    public  void defend(Human person) {
        System.out.println("Рыцарь " + getName() + " защищает " + person);
    }

    @Override
    public String toString() {
        return "рыцарь " + getName() + ".";
    }
}
