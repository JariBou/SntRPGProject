package fr.snt.game.enemies;

import java.util.Properties;


public class Zombie extends Enemies {

    public Zombie(String name, int maxHealth, int attack, int armor, int goldValue) {
        super(name, maxHealth, attack, armor, goldValue);
    }

    public Zombie(Properties prop) {
        super(prop);
    }

}
