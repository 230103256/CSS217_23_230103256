import java.io.IOException;
import java.sql.SQLException;

public class SafeConfigAdapter implements ConfigService {

    private FlakyConfig config;

    public SafeConfigAdapter(FlakyConfig config) {
        this.config = config;
    }

    @Override
    public String readKey(String key) {
        try {
            return config.getRaw(key);
        } catch (IOException | SQLException e) {
            throw new ConfigNotFoundException("Config error: " + key, e);
        }
    }

    public static void main(String[] args) {
        String studentId = "230103256";

        System.out.println("Student ID: " + studentId);

        FlakyConfig config = new FlakyConfig();
        SafeConfigAdapter adapter = new SafeConfigAdapter(config);

        try {
            System.out.println("Working key: " + adapter.readKey("valid"));
            System.out.println("Error key: " + adapter.readKey("error"));
        } catch (ConfigNotFoundException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
            System.out.println("Cause: " + e.getCause().getClass().getSimpleName());
        }
    }
}

class ConfigNotFoundException extends RuntimeException {

    public ConfigNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}