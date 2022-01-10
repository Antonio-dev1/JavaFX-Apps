package com.TransportationCompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQL_Connector {
    public static final Logger logger = Logger.getLogger(SQL_Connector.class.getName());
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/vehicletransportation";
    public static final String DB_USER = "Antonio";
    public static final String DB_PASSWORD ="Antonio91972";

    public SQL_Connector() {

    }

    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }

        return connection;
    }

    public static void dbexecuteUpdate (String sqlStmt) throws SQLException,ClassNotFoundException{
        Statement stmt = null;
        Connection conn = getDBConnection();
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        }catch(SQLException e){
            System.out.println("Problem Occured at execute update operation :" + e);
            throw e;

        }finally {
            if(stmt != null){
                stmt.close();

            }
            closeConnection(conn,stmt);
        }

    }
    public static void closeConnection(Connection con , Statement stmt) throws SQLException {
        stmt.close();
        con.close();
        System.out.println("Connection is closed");
    }
}

