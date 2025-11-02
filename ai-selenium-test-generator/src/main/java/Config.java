import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String getApiKey() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));
            return props.getProperty("OPENAI_API_KEY");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read API key from config file", e);
        }
    }
}
