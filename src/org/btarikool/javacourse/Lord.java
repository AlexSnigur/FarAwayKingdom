package org.btarikool.javacourse;

public class Lord extends Person {
    private Peasant[] peasants;

    public Lord(String name) {
        super(name);
    }

    public void setPeasants(int count) {
        this.peasants = new Peasant[count];
        for (int i = 1; i <= count; i++) {
            this.peasants[i-1] = new Peasant("Peasant" + i);
        }
    }

    public int getPeasantsCount() {
        return this.peasants.length;
    }
}
