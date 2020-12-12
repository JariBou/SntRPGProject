package fr.snt.game.levels;

import fr.snt.game.enemies.bosses.Arachnea;


public class Boss1 extends BaseLevel {

    public Boss1() throws Exception {
        Arachnea boss = new Arachnea();

        System.out.println(boss.getName() + " appears!");
        while (boss.isAlive()) {
            System.out.println(boss.getName() + " - health: " + boss.getHealth());
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("Do you want to attack?  yes/no");
            choice = sc.nextLine();
            if (choice.equals("yes")) {
                player.attack(boss);
            }
            if (boss.isAlive()) {
                boss.attack(player);
            }
            player.update();
        }

        player.endLevel();
        shop();
    }

}
