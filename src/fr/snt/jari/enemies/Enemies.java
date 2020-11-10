package fr.snt.jari.enemies;

import fr.snt.jari.Player;

abstract public class Enemies{
    protected String name;
    protected int health, attack, armor;

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

    /**
     * @return true if target is dead
     */
    public boolean isDead(){
        if (this.health <= 0){
            System.out.println(this.getName() + " is dead!");
            return true;
        } else{
            return false;
        }
    }
}
