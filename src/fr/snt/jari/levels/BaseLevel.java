package fr.snt.jari.levels;

import fr.snt.jari.Player;
import fr.snt.jari.Weapons;

import java.util.Scanner;

public abstract class BaseLevel {
    protected Player player;
    protected String choice;
    protected final Scanner sc = new Scanner(System.in);
    protected final Weapons knife = new Weapons("Knife", 3, 1);
    protected final Weapons sword = new Weapons("Sword", 6, 3);

    public void shop(){
        System.out.println(player.getGold());
        System.out.println("Do you want to buy a weapon?         Your gold: " + player.getGold());
        String choice = sc.nextLine();
        if (choice.equals("yes")){
            System.out.println("List of weapons: 1-" + knife.getName() + "   cost:" + knife.getCost() + "   attack:" + knife.getAttack());
            System.out.println("2'" + sword.getName() + "   cost:" + sword.getCost() + "   attack:" + sword.getAttack());
            choice = sc.nextLine();
            if (choice.equals("1") && knife.getCost() <= player.getGold()){
                player.removeGold(knife.getCost());
                player.setWeapon(knife);
            } else if (choice.equals("2") && sword.getCost() <= player.getGold()){
                player.removeGold(sword.getCost());
                player.setWeapon(sword);
            } else {
                System.out.println("Not Enough money!\n");
                shop();
            }
        }
    }



}
