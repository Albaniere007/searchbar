package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection getConnection()throws SQLException {

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dBProdutos","postgres","123");
    }
}
