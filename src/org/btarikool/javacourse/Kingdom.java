package org.btarikool.javacourse;

// Program arguments:
// "Роберт Баратеон" "Эддард Старк" "Петир Бейлиш" "Родрик Кассель" "Джорах Мормонт"

public class Kingdom {

    public static void main(String[] args) {
        System.out.println("1) Создаются Король, два Лорда и два Рыцаря" + "\n");
        King king = new King(args[0]);
        Lord lordFirst = new Lord(args[1]);
        Lord lordSecond = new Lord(args[2]);
        Knight knightFirst = new Knight(args[3]);
        Knight knightSecond = new Knight(args[4]);
        System.out.println("\n" + "2) Рыцарь 1 должен принести оммаж Лорду 1" + "\n");
        knightFirst.oath(lordFirst);
        System.out.println("\n" + "3) Рыцарь 2 должен защитить Лорда 2" + "\n");
        knightSecond.defend(lordSecond);
        System.out.println("\n" + "4) Лорд 1 почитает Короля" + "\n");
        lordFirst.reverence(king);
        System.out.println("\n" + "5) Лорд 2 защищает короля" + "\n");
        lordSecond.defend(king);
        System.out.println("\n" + "6) Король дает Лорду 1 землю" + "\n");
        king.present(lordFirst, "землю");
        System.out.println("\n" + "7) Король дает Лорду 2 двух крестьян" + "\n");
        king.present(lordSecond, "двух крестьян");
    }

}
