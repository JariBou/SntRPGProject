package fr.snt.jari.equipables;

public class Weapons extends Equipables{
    private final int attack;

    public Weapons(String name, int attack, int cost){
        this.name = name;
        this.attack = attack;
        this.cost = cost;
    }

    public int getAttack() {
        return attack;
    }

}
