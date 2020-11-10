package fr.snt.jari;

import fr.snt.jari.enemies.Zombie;

public class main {

    public static void main(String[] args) {

        Player jb = new Player("JariBou", 50, 5, 0);
        Zombie zomb = new Zombie("Zombie1", 30, 1, 2);
        Zombie zomb2 = new Zombie("Zombie2", 30, 1, 3);

        System.out.println(jb.getHealth());
        System.out.println(zomb.getHealth());
        System.out.println(zomb2.getHealth());
        int turns = 0;
        while (!zomb.isDead()) {
            jb.attack(zomb);
            turns++;
        }
        turns = 0;
        System.out.println(zomb.getHealth());
        System.out.println(zomb2.getHealth());
        while (!zomb2.isDead()) {
            jb.attack(zomb2);
            turns++;
        }
        System.out.println(zomb.getHealth());
        System.out.println(zomb2.getHealth());


    }

}
