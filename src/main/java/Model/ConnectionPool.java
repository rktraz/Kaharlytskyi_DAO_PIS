package Model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPool {
    public static DataSource getDatasource() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("mysql");
        HikariConfig config = new HikariConfig();
        config.setUsername(resourceBundle.getString("username"));
        config.setPassword(resourceBundle.getString("password"));
        config.setJdbcUrl(resourceBundle.getString("url"));
        DataSource ds = new HikariDataSource(config);
        return ds;
    }
}
