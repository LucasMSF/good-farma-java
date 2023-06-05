package br.goodfarma.database;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LucasMSF
 */
public class Database {
    public static String database, user, password, server;
    public static int port;

    public static java.sql.Connection connection = null;

    static {
        //mysql e mariaDB
        database = "goodfarma";
        user = "root";
        password = "";
        server = "localhost";
        port = 3306;
    }

    public static void connect() throws SQLException {
        //mysql workbench
        String url = "jdbc:mysql://" + server +
                     ":" + port + "/" + database;

        //MariaDB
        //String url = "jdbc:mariadb://" + servidor +
        //             ":" + porta + "/" + bancoDados;
        
        //sqlServer
        //String url = "jdbc:sqlserver://" + servidor
        //        + ":" + porta + ";databaseName=" + bancoDados;

        connection = DriverManager.getConnection(url, user, password);
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

    public static java.sql.Connection getConnection() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is closed...");
        } else {
            return connection;
        }
    }

}
