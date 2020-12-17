package assets.utils;

import java.util.Arrays;


public class UtilMethods {

    public static String getType(Class c){
        String[] s = String.valueOf(c).split("\\.");
        return s[s.length - 1];
    }

}
