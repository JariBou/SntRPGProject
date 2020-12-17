package assets.utils;

import fr.snt.game.Player;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Weapons;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import static java.lang.String.valueOf;


public class SavesHandler {

    public void write(Properties prop, String path){
        try{
            OutputStream out = new FileOutputStream(path);
            prop.store(out, "Save File");
            out.flush();
            out.close();
        } catch (IOException e){
            System.out.println("Unexpected error while saving: " + e);
        }
    }

    public static void save(Player player, String path) throws IOException {
        Properties prop = new Properties();
        prop.setProperty("name", player.getName());
        prop.setProperty("maxHealth", valueOf(player.getMaxHealth()));
        prop.setProperty("attack", valueOf(player.getAttack()));
        prop.setProperty("armor", valueOf(player.getBaseArmor()));
        prop.setProperty("gold", valueOf(player.getGold()));
        prop.setProperty("playerLvl", valueOf(player.getPlayerLevel()) + ":" + valueOf(player.getLevelCount()));

        if (player.hasWeapon()) {
            prop.setProperty("currWeapon", player.getWeapon().getItemName());
        } else {
            prop.setProperty("currWeapon", "null");
        }if (player.hasArmor()) {
            prop.setProperty("currArmor", player.getWeapon().getItemName());
        } else {
            prop.setProperty("currArmor", "null");
        }
        StringBuilder items = new StringBuilder();
        for (Weapons w : player.getWeapons()) {
            items.append(w.getItemName()).append(":");
        }if (items.length() == 0){
            prop.setProperty("Weapons", "null");
        } else {
            prop.setProperty("Weapons", items.toString());
        }

        items = new StringBuilder();
        for (Armors a : player.getArmors()) {
            items.append(a.getItemName()).append(" : ");
        } if (items.length() == 0){
            prop.setProperty("Armors", "null");
        } else {
            prop.setProperty("Armors", items.toString());
        }

        // Save the file
        OutputStream out = new FileOutputStream(path);
        prop.store(out, null);
    }


}

