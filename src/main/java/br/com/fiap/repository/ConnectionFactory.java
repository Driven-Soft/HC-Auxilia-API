package br.com.fiap.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() throws SQLException {
        String jdbc = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
        return DriverManager.getConnection(jdbc, "rm564723", "200706");
    }
}
