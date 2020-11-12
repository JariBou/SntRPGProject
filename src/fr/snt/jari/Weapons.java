package fr.snt.jari;

public class Weapons {
    private final String name;
    private final int attack, cost;

    public Weapons(String name, int attack, int cost){
        this.name = name;
        this.attack = attack;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getCost() {
        return cost;
    }
}
