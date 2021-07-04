import fr.snt.game.enemies.Armoured_Zombie;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static assets.utils.SavesHandler.getLvlEntities;


public class stateTests {

    public static void main(String[] args) {
        System.out.println(getLvlEntities(3));
    }

}
