package fr.snt.jari.levels;

import fr.snt.jari.Player;

public class GameOverLevel extends BaseLevel{

    public GameOverLevel(Player player){
        System.out.println(player.getName() + " has no Health left!");
        System.out.println(player.getName() + " Died! Play again?");
        String choice = sc.nextLine();
        if (choice.equals("yes")){
            new Level1();
        } else{
            System.exit(1);
        }
    }

}
