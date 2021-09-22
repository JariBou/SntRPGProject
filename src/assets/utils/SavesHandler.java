package assets.utils;

import fr.snt.game.Player;
import fr.snt.game.enemies.Armoured_Zombie;
import fr.snt.game.enemies.Enemies;
import fr.snt.game.enemies.Zombie;
import fr.snt.game.enemies.bosses.Arachnea;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Weapons;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
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
        Map<String, String> data = player.test();
        Properties prop = new Properties();

        for (String field : data.keySet()) {
            prop.setProperty(field, data.get(field));
        }
//
//        prop.setProperty("name", player.getName());
//        prop.setProperty("maxHealth", valueOf(player.getMaxHealth()));
//        prop.setProperty("attack", valueOf(player.getAttack()));
//        prop.setProperty("armor", valueOf(player.getBaseArmor()));
//        prop.setProperty("gold", valueOf(player.getGold()));
//        prop.setProperty("playerLvl", player.getPlayerLevel() + ":" + player.getLevelCount());
//
//        if (player.hasWeapon()) {
//            prop.setProperty("currWeapon", player.getWeapon().getItemName());
//        } else {
//            prop.setProperty("currWeapon", "null");
//        }
//        if (player.hasArmor()) {
//            prop.setProperty("currArmor", player.getWeapon().getItemName());
//        } else {
//            prop.setProperty("currArmor", "null");
//        }
//        StringBuilder items = new StringBuilder();
//        for (Weapons w : player.getWeapons()) {
//            items.append(w.getItemName()).append(":");
//        }
//        if (items.length() == 0) {
//            prop.setProperty("Weapons", "null");
//        } else {
//            prop.setProperty("Weapons", items.toString());
//        }
//
//        items = new StringBuilder();
//        for (Armors a : player.getArmors()) {
//            items.append(a.getItemName()).append(" : ");
//        }
//        if (items.length() == 0) {
//            prop.setProperty("Armors", "null");
//        } else {
//            prop.setProperty("Armors", items.toString());
//        }

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

    // Useless for now
    public static boolean saveState(ArrayList<Enemies> enemies, Player player, String path) {
        // Save the enemies state
        for (Enemies en : enemies) {
            Class<? extends Enemies> cls = en.getClass();
            if (cls == Zombie.class){
                System.out.println("is a Zombie");
            } else if (cls == Armoured_Zombie.class) {
                System.out.println("is an armoured zombie");
            }
        }
        return true;
    }

    // Useless for now
    public static boolean saveState(ArrayList<Enemies> enemies) throws IOException {
        int saveNumber = 0;
        // Save the enemies state
        for (Enemies en : enemies) {
            saveNumber++;
            // first do common properties
            Properties prop = new Properties();
            prop.setProperty("class", en.getClass().getName());
            prop.setProperty("name", en.getName());
            prop.setProperty("health", String.valueOf(en.getHealth()));
            prop.setProperty("maxHealth", String.valueOf(en.getMaxHealth()));
            prop.setProperty("attack", String.valueOf(en.getAttack()));
            prop.setProperty("armor", String.valueOf(en.getArmor()));
            prop.setProperty("goldValue", String.valueOf(en.getGoldValue()));
            prop.setProperty("burn", String.valueOf(en.getBurn()));
            prop.setProperty("burnLvl", String.valueOf(en.getBurnLvl()));
            prop.setProperty("frozenTurns", String.valueOf(en.getFrozenTurns()));
            prop.setProperty("frozenTurns", String.valueOf(en.getParaChance()));
            prop.setProperty("paraTurns", String.valueOf(en.getParaTurns()));

            String[] cls = en.getClass().getName().split("\\.");
            switch (cls[cls.length-1]) {
                // First test if is a 'regular' entity
                case "Zombie" -> System.out.println("is a Zombie");
                case "Armoured_Zombie" -> {
                    System.out.println("is an armoured zombie");
                    prop.setProperty("gTurns", String.valueOf(((Armoured_Zombie) en).getgTurns()));
                }
                // Else it means that it is a boss
                default -> System.out.println("is a Boss");
            }

            // Save the file
            OutputStream out = new FileOutputStream("src\\fr\\snt\\game\\temp\\Entity" + saveNumber + ".properties");
            prop.store(out, null);

        }
        return true;
    }

    public static ArrayList<Enemies> getLvlEntities(int level) {
        String path = "src\\fr\\snt\\game\\levels\\level" + level;
        File f = new File(path);
        File[] listFiles = f.listFiles();
        ArrayList<Enemies> enemiesArrayList = new ArrayList<>();
        assert listFiles != null;
        for (File s : listFiles) {
            Properties prop = new Properties();
            try {
                prop.load(new FileInputStream(path + "\\" + s.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] cls = prop.getProperty("class").split("\\.");
            switch (cls[cls.length-1]) {
                case "Zombie" -> enemiesArrayList.add(new Zombie(prop));
                case "Armoured_Zombie" -> enemiesArrayList.add(new Armoured_Zombie(prop));
                case "Arachnea" -> enemiesArrayList.add(new Arachnea(prop));

                // Else it means that there is a problem, or I didn't create the shit
                default -> System.out.println("problem:" + cls[cls.length-1]);
            }

        }
        return enemiesArrayList;
    }

}

