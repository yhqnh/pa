package com.ikamobile.tmcs.generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://33.33.33.33:3306/tmcs?useUnicode=true&characterEncoding=UTF-8&useInformationSchema=true", "root", "121212");
        try {
            
            printForeignKeys(connection, "flight_segments");
            
        } finally {
            connection.close();
        }
    }

    private static void printForeignKeys(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
        while (foreignKeys.next()) {
            String fkTableName = foreignKeys.getString("FKTABLE_NAME");
            String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
            String pkTableName = foreignKeys.getString("PKTABLE_NAME");
            String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
            String seq = foreignKeys.getString("KEY_SEQ");
            System.out.println("[" +seq + "]" + fkTableName + "." + fkColumnName + " -> " + pkTableName + "." + pkColumnName);
        }
    }
    
}
