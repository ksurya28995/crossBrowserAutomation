package commonUtilities;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Class is used to load the values/properties from the configuration file
 * 
 * @return returns the value for the key from the config file
 * 
 * @author SuryaRay
 */
public class PropertyManager {
	final static String filename = "appConfig.properties";
	static InputStream inpStream = null;
	static Properties prop = new Properties();
	public static String getPropertyVal(String key) {
		try {
			inpStream = PropertyManager.class.getClassLoader().getResourceAsStream(filename);
			if(inpStream==null) {
				System.out.println("Unable to find the "+filename);
				throw new FileNotFoundException();
			}
			prop.load(inpStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key.toLowerCase());
	}
}
