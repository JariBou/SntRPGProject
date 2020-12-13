package fr.snt.game.enemies;

public class Zombie extends Enemies {

    public Zombie(String name, int maxHealth, int attack, int armor, int goldValue) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.attack = attack;
        this.armor = armor;
        this.goldValue = goldValue;
    }

}
