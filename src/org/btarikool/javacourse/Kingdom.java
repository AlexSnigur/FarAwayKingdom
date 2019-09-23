package org.btarikool.javacourse;

public class Kingdom {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Pass names as arguments!");
            return;
        }

        King king = new King(args[0]);
        Lord lord1 = new Lord(args[1]);
        Lord lord2 = new Lord(args[2]);
        Knight knight1 = new Knight(args[3]);
        Knight knight2 = new Knight(args[4]);

        knight1.perform("I offer you homage, " + lord1.getName());
        knight2.perform("I will protect you, " + lord2.getName());
        lord1.perform("I obey you, " + king.getName());
        lord2.perform("I will protect you," + king.getName());
        king.perform("I give you land, " + lord1.getName());
        king.perform("I give you two peasants, " + lord2.getName());
        lord2.setPeasants(2);
        System.out.println("Count of Lord 2's peasants: " + lord2.getPeasantsCount());
    }
}
