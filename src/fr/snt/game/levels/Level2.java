package fr.snt.game.levels;

import fr.snt.game.enemies.Zombie;

public class Level2 extends BaseLevel{

    public Level2(){
        System.out.println(player.getItemInInventory(0).getName());

        Zombie zombie1 = new Zombie("Zombie 1", 20, 3, 1, 3);

        System.out.println("A Zombie appears! \nWill you fight him? yes/no");
        String choice = sc.nextLine();
        if (choice.equals("yes")){
            System.out.println("You decided to fight! Good soldier!");
        } else{
            System.out.println("Na you are brave so yo fight him!");
        }
        while (zombie1.isAlive()){
            System.out.println("Zombie 1  -  health: " + zombie1.getHealth());
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("Do you want to attack?  yes/no");
            choice = sc.nextLine();
            if (choice.equals("yes")){
                player.attack(zombie1);
            }
            if (zombie1.isAlive()) {
                zombie1.attack(player);
            }
            player.update();
        }

        player.endLevel();
        shop();
        new Boss1();
        //Start Here
    }
}
