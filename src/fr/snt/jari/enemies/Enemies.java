package fr.snt.jari.enemies;

import fr.snt.jari.Player;

abstract public class Enemies{
    protected String name;
    protected int health, attack, armor, goldValue;

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

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void attack(Player target){
        int totalDamage = this.getAttack() - target.getArmor();
        target.damage(totalDamage);
    }

    public int getGoldValue(){
        return this.goldValue;
    }

    /**
     * @return true if target is dead
     */
    public boolean isDead(){
        if (this.health <= 0){
            return true;
        } else{
            return false;
        }
    }
}
