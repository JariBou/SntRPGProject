package fr.snt.jari.levels;

import fr.snt.jari.Player;
import fr.snt.jari.Weapons;
import fr.snt.jari.enemies.bosses.Arachnea;

import java.util.ArrayList;

public class Boss1 extends BaseLevel{

    public Boss1(Player player, ArrayList<Weapons> weaponsList){
        this.player = player;
        Arachnea boss = new Arachnea();

        System.out.println(boss.getName() + " appears!");
        while (boss.isAlive()){
            System.out.println(boss.getName() + " - health: " + boss.getHealth());
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("Do you want to attack?  yes/no");
            choice = sc.nextLine();
            if (choice.equals("yes")){
                player.attack(boss);
            }
            if (boss.isAlive()) {
                boss.attack(player);
            }
            player.update();
        }

        player.endLevel();
        shop(weaponsList);
    }

}
