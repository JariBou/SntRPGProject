package assets.utils;

import fr.snt.game.Player;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Weapons;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.String.valueOf;


public class SavesHandler {

    public void write(Properties prop, String path) {
        try {
            OutputStream out = new FileOutputStream(path);
            prop.store(out, "Save File");
            out.flush();
            out.close();
        } catch (IOException e) {
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
        prop.setProperty("playerLvl", player.getPlayerLevel() + ":" + player.getLevelCount());

        if (player.hasWeapon()) {
            prop.setProperty("currWeapon", player.getWeapon().getItemName());
        } else {
            prop.setProperty("currWeapon", "null");
        }
        if (player.hasArmor()) {
            prop.setProperty("currArmor", player.getWeapon().getItemName());
        } else {
            prop.setProperty("currArmor", "null");
        }
        StringBuilder items = new StringBuilder();
        for (Weapons w : player.getWeapons()) {
            items.append(w.getItemName()).append(":");
        }
        if (items.length() == 0) {
            prop.setProperty("Weapons", "null");
        } else {
            prop.setProperty("Weapons", items.toString());
        }

        items = new StringBuilder();
        for (Armors a : player.getArmors()) {
            items.append(a.getItemName()).append(" : ");
        }
        if (items.length() == 0) {
            prop.setProperty("Armors", "null");
        } else {
            prop.setProperty("Armors", items.toString());
        }

        // Save the file
        OutputStream out = new FileOutputStream(path);
        prop.store(out, null);
    }

    public static boolean testLoad() throws Exception {
        File f = new File("src\\fr\\snt\\game\\equipables\\armors");
        File[] listFiles = f.listFiles();
        try {
            assert listFiles != null;
            for (File s : listFiles) {
                if (!s.getName().endsWith("example.properties")) {
                    new Armors(s.getName().replace(".properties", ""));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while loading Armors: " + e);
        }
        f = new File("src\\fr\\snt\\game\\equipables\\weapons");
        listFiles = f.listFiles();
        try {
            assert listFiles != null;
            for (File s : listFiles) {
                if (!s.getName().endsWith("example.properties")) {
                    new Weapons(s.getName().replace(".properties", ""));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while loading Weapons: " + e);
        }
        return true;
    }

    public static ArrayList<Armors> loadA() throws Exception {
        File f = new File("src\\fr\\snt\\game\\equipables\\armors");
        File[] listFiles = f.listFiles();
        ArrayList<Armors> AL = new ArrayList<>();
        assert listFiles != null;
        for (File s : listFiles) {
            if (!s.getName().endsWith("example.properties")) {
                AL.add(new Armors(s.getName().replace(".properties", "")));
            }
        }
        return AL;
    }

    public static ArrayList<Weapons> loadW() throws Exception {
        File f = new File("src\\fr\\snt\\game\\equipables\\weapons");
        File[] listFiles = f.listFiles();
        ArrayList<Weapons> WL = new ArrayList<>();
        assert listFiles != null;
        for (File s : listFiles) {
            if (!s.getName().endsWith("example.properties")) {
                WL.add(new Weapons(s.getName().replace(".properties", "")));
            }
        }
        return WL;
    }

}

