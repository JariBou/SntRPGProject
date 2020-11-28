package fr.snt.jari.enemies;

import fr.snt.jari.Player;

import java.util.Random;

abstract public class Enemies{
    // Base attributes
    protected String name;
    protected int health, attack, armor, goldValue;
    // Dodge mechanic
    protected Random rd = new Random();
    public boolean dodged = false;
    public boolean guarded = false;

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public void damage(int attack){
        this.health -= attack;
    }

    public int getArmor() {
        return armor;
    }

    public void attack(Player target){
        int totalDamage = this.getAttack() - target.getBaseArmor();
        target.damage(totalDamage);
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
    }

    public int getGoldValue(){
        return this.goldValue;
    }

    /**
     * @return true if target is Alive
     */
    public boolean isAlive(){
        return this.health > 0;
    }
}
