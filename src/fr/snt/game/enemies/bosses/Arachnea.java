package fr.snt.game.enemies.bosses;

import fr.snt.game.Player;
import fr.snt.game.enemies.Enemies;


public class Arachnea extends Enemies {

    public Arachnea() {
        // To balance
        this.name = "Arachnea";
        this.health = 100;
        this.attack = 15;
        this.armor = 7;
        this.goldValue = 15;
    }

    @Override
    public void attack(Player target) {
        int totalDamage = this.getAttack() - target.getBaseArmor();
        target.damage(totalDamage);
        double poison_chance = rand();
        if (poison_chance < 0.08) { // 8% chance
            target.setPoison(3, 2);
        }
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
    }

}
