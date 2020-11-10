package fr.snt.jari.enemies;

public class Weapons {
    private String name;
    private int attack;

    public void Weapon(String name, int attack){
        this.name = name;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }
}
