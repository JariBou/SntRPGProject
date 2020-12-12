package fr.snt.game.levels;

import fr.snt.game.Player;
import fr.snt.game.enemies.Armoured_Zombie;
import fr.snt.game.equipables.armors.Armors;
import fr.snt.game.equipables.weapons.Weapons;

import java.util.ArrayList;


public class Level1 extends BaseLevel {

    public Level1() {
        WL = new ArrayList<Weapons>();
        AL = new ArrayList<Armors>();


        WL.add(new Weapons("Knife", 2, 2));
        WL.add(new Weapons("Sword", 6, 5));


        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        player = new Player(name, 24, 5, 1);
        Armoured_Zombie zombie1 = new Armoured_Zombie("Zombie 1", 20, 3, 1, 3);

        System.out.println("A Zombie appears! \nWill you fight him? yes/no");
        String choice = sc.nextLine();
        if (choice.equals("yes")) {
            System.out.println("You decided to fight! Good soldier!");
        } else {
            System.out.println("Na you are brave so yo fight him!");
        }
        while (zombie1.isAlive()) {
            System.out.println("Zombie 1  -  health: " + zombie1.getHealth());
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("Do you want to attack?  yes/no");
            choice = sc.nextLine();
            if (choice.equals("yes")) {
                player.attack(zombie1);
            }
            if (zombie1.isAlive()) {
                zombie1.attack(player);
            }
            player.update();
            zombie1.update();
        }

        player.endLevel();
        shop();

        // Bla Bla Bla

        // In the end
        new Level2();
    }

}
