package fr.snt.jari;

import fr.snt.jari.enemies.Zombie;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Player jb = new Player("JariBou", 50, 5, 0);
        Zombie zomb = new Zombie("Zombie1", 30, 1, 2, 1);
        Zombie zomb2 = new Zombie("Zombie2", 30, 1, 3, 2);
        Weapons knife = new Weapons("Knife", 3, 1);


        Scanner sc = new Scanner(System.in);
        System.out.println("Hello " + jb.getName() + "!");
        while(!zomb.isDead()) {
            System.out.println("Zombie1 health: " + zomb.getHealth() + "\n");
            System.out.println("Attack? yes/no");
            String choice = sc.nextLine();
            if (choice.equals("yes")) {
                jb.attack(zomb);
                System.out.println(jb.getName() + " attacked for " + jb.getTotalDamage() + " damage!");
            }
        }
        System.out.println(jb.getGold());
        System.out.println("Do you want to buy a weapon?");
        String choice = sc.nextLine();
        if (choice.equals("yes")){
            System.out.println("List of weapons: 1-" + knife.getName());
            choice = sc.nextLine();
            if (choice.equals("1") || knife.getCost() <= jb.getGold()){
                jb.removeGold(knife.getCost());
                jb.setWeapon(knife);
            }
        }
         while(!zomb2.isDead()) {
            System.out.println("Zombie1 health: " + zomb2.getHealth() + "\n");
            System.out.println("Attack? yes/no");
            choice = sc.nextLine();
            if (choice.equals("yes")) {
                jb.attack(zomb2);
                System.out.println(jb.getName() + " attacked for " + jb.getTotalDamage() + " damage!");
            }
        }





    }

}
