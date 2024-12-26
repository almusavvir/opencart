package propUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    Properties prop = new Properties();
    File file = new File("config.properties");
    FileInputStream fis = new FileInputStream(file);

    public PropertiesUtil() throws FileNotFoundException {
    }

    public String getBaseUrl() throws IOException {
        prop.load(fis);
        return prop.getProperty("baseUrl");
    }
    public String getLoginEmail() throws IOException {
        prop.load(fis);
        return prop.getProperty("loginEmail");
    }
    public String getLoginPassword() throws IOException {
        prop.load(fis);
        return prop.getProperty("loginPassword");
    }
}
