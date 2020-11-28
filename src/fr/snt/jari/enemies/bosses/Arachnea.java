package fr.snt.jari.enemies.bosses;

import fr.snt.jari.Player;
import fr.snt.jari.enemies.Enemies;

public class Arachnea extends Enemies {

    public Arachnea(){
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
        int poison_chance = rd.nextInt(15);
        if (poison_chance == 7){
            target.setPoison(3, 2);
        }
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
    }
}
