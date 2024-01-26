package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	
	
	
	public static String readProperties() {
		String endPoint;
		Properties properties = new Properties();
		try {
			String path = System.getProperty(("user.dir"));
			FileInputStream fileInputStream = new FileInputStream(path+"\\src\\test\\resource\\routes.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		endPoint = properties.getProperty("EndpointURL");
		return endPoint;
	}

}
