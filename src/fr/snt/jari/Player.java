package fr.snt.jari;

import fr.snt.jari.enemies.Enemies;
import fr.snt.jari.levels.GameOverLevel;

public class Player {
    private final String name;
    private int health, maxHealth, attack, gold;
    private final int armor;
    private Weapons weapon;
    // Player Levels and poison mechanics
    private int levelCount, poison, poisonLevel;

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
        System.out.println("Successfully equipped '" + weapon.getName() + "'!");
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

    private void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    private void levelUp(){
        this.setMaxHealth(getMaxHealth() + 3);
        levelCount++;
        if (levelCount == 4){
            levelCount = 0;
            this.setAttack(getAttack() + 1);
        }
    }

    private void resetHealth() {
        this.health = this.maxHealth;
    }

    /**
     *  To be called at the end of a level
     */
    public void endLevel(){
        setPoison(0, 0);
        levelUp();
        resetHealth();
    }

    public boolean hasWeapon(){
        return this.weapon != null;
    }

    public int getArmor() {
        return armor;
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

    private int getTotalDamage(){
        if (this.hasWeapon()){
            return this.getAttack() + weapon.getAttack();
        } else{
            return this.getAttack();
        }
    }

    public void setPoison(int turns, int poisonLevel){
        this.poison = turns;
        this.poisonLevel = poisonLevel;
    }

    /**
     * To call at the end of EACH turn
     */
    public void update(){
        if (isDead()){
                new GameOverLevel(this);
            }
        else if (poison > 0){
            this.health -= poisonLevel;
            poison--;
        }
    }

    public void attack(Enemies target){
        int totalDamage = this.getTotalDamage() - target.getArmor();
        target.damage(totalDamage);
        if (!target.dodged) {
            if (target.guarded){
                System.out.println(this.getName() + " attacked " + target.getName() + " for " + (totalDamage - target.getArmor()) + " damage!");
            } else {
                System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
                if (!target.isAlive()) {
                    System.out.println(target.getName() + " is dead!");
                    this.addGold(target.getGoldValue());
                    System.out.println("You were awarded with " + target.getGoldValue() + "gold!");
                }
            }
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
