package org.btarikool.javacourse;

public class Kingdom {

    public static void main(String[] args) {
        // write your code here

        if (args.length != 2) {
            System.out.println("Pass names as arguments!");
            return;
        }

        String kingName = args[0];
        String lordName = args[1];

        King king = new King(kingName);
        Lord lord = new Lord(lordName);

        System.out.println("Far far away Kingdom");

        lord.say("Hello, King " + king.getName());

        king.say("Hello, " + lord.getName());
    }
}
