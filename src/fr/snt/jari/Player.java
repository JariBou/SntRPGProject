package fr.snt.jari;

import fr.snt.jari.enemies.Enemies;

public class Player {
    private final String name;
    private int health,maxHealth, attack, armor, gold;
    private Weapons weapon;

    public Player(String name, int maxHealth, int attack, int armor){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.armor = armor;
        this.weapon = null;
        this.gold = 0;
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

    public int getGold() {
        return gold;
    }

    public void addGold(int amount){
        this.gold += amount;
    }

    public void removeGold(int amount){
        this.gold -= amount;
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

    public int getTotalDamage(){
        if (this.hasWeapon()){
            return this.getAttack() + weapon.getAttack();
        } else{
            return this.getAttack();
        }
    }

    public void attack(Enemies target){
        int totalDamage = this.getTotalDamage() - target.getArmor();
        target.damage(totalDamage);
        if (target.isDead()){
            System.out.println(target.getName() + " is dead!");
            this.addGold(target.getGoldValue());
        }
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
