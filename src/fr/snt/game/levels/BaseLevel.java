package fr.snt.game.levels;

import fr.snt.game.Player;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Weapons;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseLevel {
    protected final Scanner sc = new Scanner(System.in);
    protected Player player;
    protected String choice;
    private Weapons weapon;
    private Armors armor;


    public void shop(ArrayList<Weapons> WL, ArrayList<Armors> AL) {
        System.out.println(player.getGold());
        System.out.println("Do you want to buy Something?         Your gold: " + player.getGold());
        String choice = sc.nextLine();
        while (true) {
            if (choice.equals("yes")) {
                System.out.println("1. Buy weapons \n2. Buy armours");
                choice = sc.nextLine();
                if (choice.equals("1")) {
                    int count = 1;
                    System.out.println("Weapons list: \n");
                    for (Weapons i : WL) {
                        System.out.println(count + ". " + i.getName() + "   Attack: " + i.getAttack() + "   Cost: " + i.getCost());
                        count++;
                    }
                    choice = sc.nextLine();
                    try {
                        this.weapon = WL.get(Integer.parseInt(choice) - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input: " + e);
                        shop(WL, AL);
                    }
                    if (weapon.getCost() <= player.getGold()) {
                        // Buyable
                        player.removeGold(weapon.getCost());
                        player.setWeapon(weapon);
                        player.addToInventory(weapon);
                        WL.remove(weapon);
                        System.out.println("Do you want to buys something else?");
                        choice = sc.nextLine();
                        if (choice.equals("no")) {
                           break;
                        }

                    } else {
                        System.out.println("Not Enough money!\n");
                        shop(WL, AL);
                    }
                }

                if (choice.equals("2")) {
                    //Buy armour
                    int count = 1;
                    System.out.println("Armors list: \n");
                    for (Armors i : AL) {
                        System.out.println(count + ". " + i.getName() + "   Armour: " + i.getArmorValue() + "   Cost: " + i.getCost());
                        count++;
                    }
                    choice = sc.nextLine();
                    try {
                        this.armor = AL.get(Integer.parseInt(choice) - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input: " + e);
                        shop(WL, AL);
                    }
                    if (armor.getCost() <= player.getGold()) {
                        // Buyable
                        player.removeGold(armor.getCost());
                        player.setArmor(armor);
                        player.addToInventory(armor);
                        AL.remove(armor);
                        System.out.println("Do you want to buys something else?");
                        choice = sc.nextLine();
                        if (choice.equals("no")) {
                           break;
                        }

                    } else {
                        System.out.println("Not Enough money!\n");
                        shop(WL, AL);
                    }
                }

            }
        }
        System.out.println();
    }
}
