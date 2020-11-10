package fr.snt.jari;

import fr.snt.jari.enemies.Enemies;
import fr.snt.jari.enemies.Weapons;

public class Player {
    private final String name;
    private int health,maxHealth, attack, armor;
    private Weapons weapon;

    public Player(String name, int maxHealth, int attack, int armor){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.armor = armor;
        this.weapon = null;
    }

    public String getName() {
        return name;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon.getName();
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void resetHealth() {
        this.health = this.maxHealth;
    }

    public boolean hasWeapon(){
        return this.weapon != null;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int attack){
        this.health -= attack;
    }

    public void attack(Enemies target){
        int totalDamage = this.getAttack() + weapon.getAttack() - target.getArmor();
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
