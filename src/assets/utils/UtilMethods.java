package assets.utils;

import java.util.Arrays;


@SuppressWarnings("rawtypes")
public class UtilMethods {

    public static String getType(Class c){
        String[] s = String.valueOf(c).split("\\.");
        return s[s.length - 1];
    }

}
