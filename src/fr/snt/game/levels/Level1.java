package fr.snt.game.levels;

import assets.utils.SavesHandler;
import fr.snt.game.Player;
import fr.snt.game.enemies.Armoured_Zombie;
import fr.snt.game.equipables.Weapons;

import static assets.utils.SavesHandler.testLoad;
import static assets.utils.SavesHandler.loadA;
import static assets.utils.SavesHandler.loadW;


public class Level1 extends BaseLevel {

    public Level1() throws Exception {

        assert testLoad();
        WL = loadW();
        AL = loadA();

        System.out.println(WL.get(2).getName());


        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        player = new Player(name, 24, 5, 1);
        Armoured_Zombie zombie1 = new Armoured_Zombie("Zombie 1", 20, 3, 1, 3);

        player.addToInventory(WL.get(1));
        player.addToInventory(WL.get(0));
        player.setWeapon(WL.get(1));

        SavesHandler.save(player, "src\\assets\\save1.properties");


        combat(zombie1);

        player.endLevel();
        shop();

        // Bla Bla Bla

        // In the end
        new Level2();
    }

}
