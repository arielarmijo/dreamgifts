package com.ipsoflatus.dreamgifts.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/dreamgifts";
    private static final String USUARIO = "dreamgifts";
    private static final String PASSWORD = "dreamgifts";
    
    public static Connection getConnection() {
        Connection conn =  null;
        try{
         conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
    }
    
}
