package fr.snt.game.enemies;

public class Zombie extends Enemies {

    public Zombie(String name, int health, int attack, int armor, int goldValue) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.goldValue = goldValue;
    }

}
