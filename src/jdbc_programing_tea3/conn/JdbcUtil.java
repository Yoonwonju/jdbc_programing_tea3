package jdbc_programing_tea3.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    public static Connection getConnection() {
        Connection conn = null;
        String proptiesPath = "db.properties";
        try (InputStream is = ClassLoader.getSystemResourceAsStream(proptiesPath)) {
            Properties props = new Properties();
            props.load(is);
            
            String url = props.getProperty("url");
            conn = DriverManager.getConnection(url, props);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
