/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MMM
 */
public class QueriesSQL {
    public static void addItemSQL(itemsAndTransactions.InventoryItem newInventoryItem){
    
    
    }
    
    public static boolean SimpleQuery(String newQuery,boolean check){

        String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        System.out.println("");
        try {
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            statement.execute(newQuery);
            check=true;

             } catch (ClassNotFoundException  e) {
                  System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        return check;
    }
    
    
    

}
