package fr.snt.game.levels;

import fr.snt.game.Player;
import fr.snt.game.enemies.Enemies;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Equipables;
import fr.snt.game.equipables.Weapons;

import java.util.ArrayList;
import java.util.Scanner;

import static assets.utils.UtilMethods.getType;


public abstract class BaseLevel {
    static Player player;
    static ArrayList<Weapons> WL;
    static ArrayList<Armors> AL;
    final Scanner sc = new Scanner(System.in);
    String choice, message;
    private Weapons weapon;
    private Armors armor;

    // TODO: group repeated parts of the code into functions
    // FIXME: Check that there cannot be any error when choosing

    public void shop() {
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
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Wrong input: " + e);
                        shop();
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
                        shop();
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
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Wrong input: " + e);
                        shop();
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
                        shop();
                    }
                } else {
                    System.out.println("Wrong input!");
                    shop();
                }

            }
        }
        System.out.println();
    }

    public void combat(Enemies mob) throws Exception {
        System.out.println(mob.getName() + " appears! \nWill you fight him? yes/no");
        String choice = sc.nextLine();
        if (choice.equals("yes")) {
            System.out.println("You decided to fight! Good soldier!");
        } else {
            System.out.println("Na you are brave so yo fight him!");
        }
        while (mob.isAlive()) {
            System.out.println(mob.getName() + " -  health: " + mob.getHealth());
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("Do you want to attack?  yes/no");
            choice = sc.nextLine();
            if (choice.equals("yes")) {
                player.attack(mob);
            }
            if (mob.isAlive()) {
                mob.attack(player);
            }
            player.update();
            mob.update();
        }
    }

    private void displayInventory(){
        int count = 1;
        message = player.hasWeapon() ? ("Equipped weapon: " + player.getWeapon().getName()) : ("Equipped weapon: None");
        message += player.hasArmor() ? ("\nEquipped Armor: " + player.getArmor().getName()) : ("Equipped Armor: None");
        System.out.println(message);
        for (Equipables e : player.getInventory()){
            System.out.println(count + ". " + e.getName() + "     Type: " + getType(e.getClass()));
            count++;
        }
    }

    private int choose(){
        choice = sc.nextLine();
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException e){
            System.out.println("Wrong input! Try again");
            choose();
        }
        return 0;
    }

    public void equiDialogW(Equipables item){
        System.out.println("Do you want to equip the Weapon?");
        choice = sc.nextLine();
        if (choice.equals("yes")){
            player.setWeapon((Weapons) (item));
        } else if (!choice.equals("no")){
            System.out.println("Wrong input! Try again");
            equiDialogW(item);
        }
    }

    public void equiDialogA(Equipables item){
        System.out.println("Do you want to equip the Armor?");
        choice = sc.nextLine();
        if (choice.equals("yes")){
            player.setArmor((Armors) (item));
        } else if (!choice.equals("no")){
            System.out.println("Wrong input! Try again");
            equiDialogA(item);
        }
    }

    private void manageItem(int nb){
        nb--;
        Equipables item = player.getItemInInventory(nb);
        if (getType(item.getClass()).equals("Weapons")){
            System.out.println(item.getName());
            System.out.println("attack: " + ((Weapons) item).getAttack());
            System.out.println("Description: " + item.getDescription());
            if (item.hasSpEffect()){
                System.out.println("SP effect: " + item.getSpEffectType());
                System.out.println(((Weapons) item).getSPs().toString());
            }
            if (player.getWeapon() != item){
                equiDialogW(item);
            }
        }else{
            System.out.println(item.getName());
            System.out.println("armor: " + ((Armors) item).getArmorValue());
            System.out.println("Description: " + item.getDescription());
            if (item.hasSpEffect()){
                System.out.println("SP effect: " + item.getSpEffectType());
                System.out.println(((Armors) item).getSPs());
            }
            if (player.getArmor() != item){
                equiDialogA(item);
            }

        }
        manageItem1();
    }

    public void manageItem1(){
        System.out.println("Would you like to manage another item?");
        choice = sc.nextLine();
        if (choice.equals("yes")){
            manage1();
        } else if (!choice.equals("no")){
            System.out.println("Wrong input! Try again");
        }
    }

    public void manage(){
        System.out.println("Would you like to manage you inventory?");
        choice = sc.nextLine();
        if (choice.equals("yes")){
            manage1();
        } else if (choice.equals("no")){
            return;
        }else{
            System.out.println("Wrong input");
            manage();
        }
    }

    public void manage1(){
        displayInventory();
        int nb = choose();
        manageItem(nb);
    }

}
