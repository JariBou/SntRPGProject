package fr.snt.game.enemies;

import fr.snt.game.Player;


public class Armoured_Zombie extends Enemies {
    private int gTurns = 0;

    public Armoured_Zombie(String name, int maxHealth, int attack, int armor, int goldValue) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.attack = attack;
        this.armor = armor;
        this.goldValue = goldValue;
    }

    @Override
    public void damage(int attack) {

        double dodge_chance = rand();
        if (dodge_chance < 0.1) { // 10%
            System.out.println("Dodged! No damage inflicted");
            dodged = true;
        } else if (guarded) {
            System.out.println("Guarded! Damage reduced by Armor");
            this.health -= attack - this.armor;
            guarded = false;
        } else {
            this.health -= attack;
            dodged = false;
        }

    }

    @Override
    public void attack(Player target) {
        super.attack(target);
        gTurns++;
        if (gTurns == 3) {
            guarded = true;
        }
    }

}
