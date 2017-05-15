/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsAndTransactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MMM
 */
public class AddItem {
    public static void addItem(String newCode,String newName,int newItemQ,String newUM,int newPrice){
        InventoryItem newItem=new InventoryItem(newCode);
        newItem.setName(newName);
        newItem.setUm(newUM);
        newItem.setItemQ(newItemQ);
        newItem.setPrice(newPrice);
        
        String newText="INSERT INTO ITEMS (CODE,NAME ,Q, UM, PRICE)VALUES ('"+newCode+"','"+newName+"',"+newItemQ+",'"+newUM+"',"+newPrice+")";
        boolean check=false;
        //System.out.println(newText);
        
        check=sqlPackage.QueriesSQL.SimpleQuery(newText,check);
        //System.out.println("\n"+check);
        
    
    };
    
    public static boolean ifExist(String newAddCode){
        boolean check=false;
    
        String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String currentCode = "";
            
            String query="SELECT CODE FROM ITEMS WHERE CODE='"+newAddCode+"'";
            //System.out.println(query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                currentCode = resultSet.getString(1);
                if (currentCode.equals(newAddCode)){
                    check=true;
                }
            }
        }catch(ClassNotFoundException e){
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
    public static String listAll(){
       String allItems="";

       String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
       String driver = "org.apache.derby.jdbc.ClientDriver";
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;

       try {
           Class driverClass = Class.forName(driver);
           connection = DriverManager.getConnection(url);
           statement = connection.createStatement();

           resultSet = statement.executeQuery("SELECT * FROM ITEMS");
           String currentCode = ""; int currentQ = 0;
           String currentName = "", currentUM = "",currentPrice = "";
           allItems="Code \tName  \tQ   \tUM\tPrice\tValue"+"\n-----\t------\t----\t--\t----\t-----";
           float totalValue=0.0f;

           while (resultSet.next()) {
                   currentCode = resultSet.getString(1);
                   currentName = resultSet.getString(2);
                   currentQ = Integer.parseInt(resultSet.getString(3));
                   currentUM = resultSet.getString(4);
                   currentPrice = resultSet.getString(5);
                   
                   Float value=currentQ*Float.parseFloat(currentPrice);
                   String valueF = String.format("%.02f", value);
                   value=Float.parseFloat(valueF);
                   
                   
                   totalValue+=value;
                   allItems=allItems+"\n"+currentCode+"\t"+currentName+"\t"+currentQ+"\t"+currentUM+"\t"+currentPrice+"\t"+value;
                }
           
                String totalValueF = String.format("%.02f", totalValue);
                totalValue=Float.parseFloat(totalValueF);
           
                allItems=allItems+"\n-----\t------\t----\t--\t----\t-----\nTotal "+
                       "\t      \t    \t  \t    \t"+totalValue;
                
           }catch(ClassNotFoundException e){
               System.out.println(e.getMessage());
            }catch(NumberFormatException e){
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

       return allItems;
    }

/* public static String listAllItems(){
     String allItems="";
    
        String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("SELECT CODE,NAME FROM ITEMS");
            String currentCode = "";
            String currentName = "", currentQ = "",currentUM = "",currentPrice = "";
            allItems="Code \tName  "+"\n-----\t------";
            int totalValue=0;
            
            while (resultSet.next()) {
                currentCode = resultSet.getString(1);
                currentName = resultSet.getString(2);
                allItems=allItems+"\n"+currentCode+"\t"+currentName;
            }
            
        }catch(ClassNotFoundException e){
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
    
    return allItems;
    }
*/
    public static InventoryItem getQAndPrice(String newAddCode){
    
    
        String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        InventoryItem resultInventoryItem=new InventoryItem(newAddCode);
        
        try {
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            
            int q = 0;
            float price=0;
            
            String query="SELECT Q,Price FROM ITEMS WHERE CODE='"+newAddCode+"'";
            //System.out.println(query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                q = Integer.parseInt(resultSet.getString(1));
                price=Float.parseFloat(resultSet.getString(2));
                resultInventoryItem.setItemQ(q);
                resultInventoryItem.setPrice(price);
            }
            
        }catch(ClassNotFoundException e){
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
    
    return resultInventoryItem;
    }
    
    
       public static String listBalance(){
       String allItems="";

       String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
       String driver = "org.apache.derby.jdbc.ClientDriver";
       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;

       try {
           Class driverClass = Class.forName(driver);
           connection = DriverManager.getConnection(url);
           statement = connection.createStatement();

           resultSet = statement.executeQuery("SELECT * FROM ITEMS");
           String currentCode = ""; int currentQ = 0;
           String currentName = "", currentUM = "",currentPrice = "";
           allItems="Code \tName  \tUM\tQ in  \tQ out \tQ final\tPrice\tValue"+"\n-----\t------\t----\t--\t----\t-----";
           float totalValue=0.0f;

           while (resultSet.next()) {
                   currentCode = resultSet.getString(1);
                   currentName = resultSet.getString(2);
                   currentQ = Integer.parseInt(resultSet.getString(3));
                   currentUM = resultSet.getString(4);
                   currentPrice = resultSet.getString(5);
                   
                   Float value=currentQ*Float.parseFloat(currentPrice);
                   String valueF = String.format("%.02f", value);
                   value=Float.parseFloat(valueF);
                   
                   
                   totalValue+=value;
                   allItems=allItems+"\n"+currentCode+"\t"+currentName+"\t"+currentUM+"\t"+AddTransaction.SumQCodeType(currentCode,"in")
                           +"\t"+ AddTransaction.SumQCodeType(currentCode,"out")+"\t"+currentQ+"\t"+currentPrice+"\t"+value;
                }
           
                String totalValueF = String.format("%.02f", totalValue);
                totalValue=Float.parseFloat(totalValueF);
           
                allItems=allItems+"\n-----\t------\t--\t----\t----\t----\t----\t-----\nTotal "+
                       "\t      \t     \t     \t     \t     \t     \t"+totalValue;
                
           }catch(ClassNotFoundException e){
               System.out.println(e.getMessage());
            }catch(NumberFormatException e){
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

       return allItems;
    }
    
}
