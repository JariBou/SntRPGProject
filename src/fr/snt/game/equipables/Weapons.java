package fr.snt.game.equipables;

public class Weapons extends Equipables{
    private final int attack;

    public Weapons(String name, int attack, int cost){
        this.name = name;
        this.attack = attack;
        this.cost = cost;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType){
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
    }

    public int getAttack() {
        return attack;
    }

}
