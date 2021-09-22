import assets.utils.SavesHandler;
import fr.snt.game.Player;
import fr.snt.game.gui.Gui;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;


public class runTests {
    final Path currentRelativePath = Paths.get("");
    String imPath = currentRelativePath.toAbsolutePath().toString();

    public static void main(String[] args) throws Exception {

        //Gui a = new Gui();
        Player player = new Player("test1.properties");
        //player.addGold(999);
        //player.test();
        player.printStats();
        Scanner sc = new Scanner(System.in);
        String action = "";
        while (!action.equals("stop")) {
            action = sc.nextLine();
            switch (action) {
                case "stop":
                    break;
                case "save":
                    SavesHandler.save(player, "src/fr/snt/game/saves/test2.properties");
                    break;
                case "load":
                    player = new Player("test2.properties");
                    break;
                case "damage":
                    int amount = parseInt(sc.nextLine());
                    player.damage(amount);
                    break;
                case "health":
                    System.out.println(player.getHealth());
                    break;
                case "stats":
                    player.printStats();

            }
        }

//        Properties prop = new Properties();
//        try {
//            prop.load(new FileInputStream("src\\fr\\snt\\game\\saves\\save1.properties"));
//        } catch (IOException e) {
//            throw new Exception("Unexpected Error while loadind '" + "hello" + "'\n" + e);
//        }
//        String Weapons = prop.getProperty("Weapons");
//        System.out.println(Weapons);
//
//        assert SavesHandler.testLoad();
//        Weapons w = new Weapons("sword");
//        Weapons weapons = new Weapons("botrk");
//        System.out.println(w);
//        System.out.println(weapons);
//
//
//        Armors a = new Armors("imperious_wall");
//        Player p = new Player("Jari", 100, 7, 0);
//        Zombie z = new Zombie("Zombie", 100, 15, 0, 2);
//        p.setArmor(a);
//        p.setWeapon(w);
//        System.out.println("Health: " + p.getHealth());
//        System.out.println("Damage: " + p.getTotalDamage(z));
//        z.setParalyzed(5);
//        z.attack(p);
//        z.update();
//        z.attack(p);
//        z.update();
//        System.out.println("Health: " + p.getHealth());
//        System.out.println("Damage: " + p.getTotalDamage(z));


    }

}
