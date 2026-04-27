import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Properties props = loadProperties();

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();

        // 1) Preferred: bundled with classes/resources
        try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
                return props;
            }
        }

        // 2) Fallbacks for IDE working-directory differences
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            return props;
        } catch (IOException ignored) {
            // Try src path next.
        }

        try (FileInputStream fis = new FileInputStream("src/config.properties")) {
            props.load(fis);
            return props;
        }
    }
}
