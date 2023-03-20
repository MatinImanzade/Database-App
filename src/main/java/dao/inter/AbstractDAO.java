package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {

    public Connection connect() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/resume";
        String userName = "root";
        String password = "Metin598";
        Connection connection = DriverManager.getConnection(url, userName, password);

        return connection;
    }
}
