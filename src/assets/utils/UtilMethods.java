package assets.utils;


import java.io.InputStream;
import org.apache.commons.io.IOUtils;


@SuppressWarnings("rawtypes")
public class UtilMethods {

    public static String getType(Class c){
        String[] s = String.valueOf(c).split("\\.");
        return s[s.length - 1];
    }

    public static String getFile(String fileName)
    {
        //Get file from resources folder
        ClassLoader classLoader = UtilMethods.class.getClassLoader();

        InputStream stream = classLoader.getResourceAsStream(fileName);

        try
        {
            if (stream == null)
            {
                throw new Exception("Cannot find file " + fileName);
            }

            return IOUtils.toString(stream);
        }
        catch (Exception e) {
            e.printStackTrace();

            System.exit(1);
        }

        return null;
    }

}
