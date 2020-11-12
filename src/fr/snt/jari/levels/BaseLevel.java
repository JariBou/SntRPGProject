package fr.snt.jari.levels;

import fr.snt.jari.Player;
import fr.snt.jari.Weapons;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseLevel {
    protected final Scanner sc = new Scanner(System.in);
    protected final Weapons knife = new Weapons("Knife", 3, 1);
    protected final Weapons sword = new Weapons("Sword", 6, 3);
    protected Player player;
    protected String choice;
    private Weapons weapon;

    public void shop(ArrayList<Weapons> WL) {
        System.out.println(player.getGold());
        System.out.println("Do you want to buy a weapon?         Your gold: " + player.getGold());
        String choice = sc.nextLine();
        if (choice.equals("yes")) {

            System.out.println("Weapons list: \n");
            for (Weapons i : WL) {
                System.out.println(i.getName() + "   Attack: " + i.getAttack() + "   Cost: " + i.getCost());
            }

            choice = sc.nextLine();
            try {
                this.weapon = WL.get(Integer.parseInt(choice) - 1);
            } catch (NumberFormatException e) {
                System.out.println("Wrong input: " + e);
                shop(WL);
            }
            if (weapon.getCost() <= player.getGold()){
                // Buyable
                player.removeGold(weapon.getCost());
                player.setWeapon(weapon);
                WL.remove(weapon);
            } else {
                System.out.println("Not Enough money!\n");
                shop(WL);
            }
        }
    }


}
