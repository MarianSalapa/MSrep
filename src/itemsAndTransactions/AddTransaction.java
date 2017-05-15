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
import java.util.ArrayList;
import sqlPackage.QueriesSQL;

/**
 *
 * @author MMM
 */
public class AddTransaction {
    public static int noOFTrans(){

        String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int noOfTransaction = 0;
        
        System.out.println("");
        try {
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT MAX(NOTR) FROM Transactions");
            
            
            
            while (resultSet.next()) {
            
                if (!(resultSet.getString(1).equals(null))){
                    noOfTransaction = Integer.parseInt(resultSet.getString(1));
                }
            }
        }catch (NullPointerException e){
                 System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
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
        return (noOfTransaction+1);
    }

    public static void addTransaction(int newNoTr, String newDate,String newType, String newCode, int newQ, float newPrice, String newDescription){
        Transaction newTrans=new Transaction(newNoTr);
        newTrans.setDate(newDate);
        newTrans.setCode(newCode);
        newTrans.setType(newType);
        newTrans.setCode(newCode);
        newTrans.setQ(newQ);
        newTrans.setPrice(newPrice);
        newTrans.setDescription(newDescription);
    
        String newText="INSERT INTO TRANSACTIONS (NOTR,DATETR ,TYPE, CODE, Q,PRICE,DESCRIPTION) VALUES ("+newNoTr+",'"+newDate+"','"+newType+"','"+newCode+"',"+newQ+","+newPrice+",'"+newDescription+"')";
        boolean check=false;
        
       check=sqlPackage.QueriesSQL.SimpleQuery(newText,check);
        //System.out.println("\n"+check);
        
        
    }    
    
    public static boolean checkQuantity (String newCode,int newQ){
        boolean check=false;
        
        InventoryItem newItem= AddItem.getQAndPrice(newCode);
        int q0=newItem.getItemQ();
        if (newQ>q0){
            check=true;}
        
        return check;
        
    }
    public static void changeCMPAndQ(String code,int newQ, float newPrice){
        InventoryItem newItem= AddItem.getQAndPrice(code);
        int q0=newItem.getItemQ();
        float price0=newItem.getPrice();
        
        int q1=q0+newQ;
        float price1=(q0*price0+newQ*newPrice)/(q0+newQ);
        String priceF = String.format("%.02f", price1);
        price1=Float.parseFloat(priceF);
        
        String query="UPDATE ITEMS SET q="+q1+",price="+price1+" where code='"+code+"'";
        QueriesSQL.SimpleQuery(query, false);
    }
    public static void decreaseQ(String code,int newQ){
        InventoryItem newItem= AddItem.getQAndPrice(code);
        int q0=newItem.getItemQ();
        int q1=q0-newQ;
        
        String query="UPDATE ITEMS SET q="+q1+" where code='"+code+"'";
        QueriesSQL.SimpleQuery(query, false);
        
        
    }
    
    public static String listAllTransactions(){
    String alltr="";
    
    String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
    String driver = "org.apache.derby.jdbc.ClientDriver";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
        
    try {
        Class driverClass = Class.forName(driver);
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();
            
        resultSet = statement.executeQuery("SELECT * FROM TRANSACTIONS");
        String currentNoTr="",currentType="",currentCode = ""; 
        String currentQ = "", currentDate = "",currentDescription = "";
        Float currentPrice=0.0F;
        alltr="NoTr \tDate      \tType\tCode \tQ    \tPrice\tDescription"+"\n-----\t----------\t---\t-----\t----\t-----\t------------";
        
        while (resultSet.next()) {
                currentNoTr = resultSet.getString(1);
                currentDate = resultSet.getString(2);
                currentType = resultSet.getString(3);
                currentCode = resultSet.getString(4);
                currentQ = resultSet.getString(5);
                currentPrice=Float.parseFloat(resultSet.getString(6));
                currentDescription = resultSet.getString(7);
                
                alltr=alltr+"\n"+currentNoTr+"\t"+currentDate+"\t"+currentType+"\t"+currentCode+"\t"+currentQ+"\t"+currentPrice+"\t"+currentDescription;
            }
    
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
    
    return alltr;
    }
    
    public static String listTransactionsCode(String newCode){
    String alltr="";
    
    String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
    String driver = "org.apache.derby.jdbc.ClientDriver";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
        
    try {
        Class driverClass = Class.forName(driver);
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();
            
        resultSet = statement.executeQuery("SELECT * FROM TRANSACTIONS WHERE CODE='"+newCode+"'");
        String currentNoTr="",currentType="",currentCode = ""; 
        String currentQ = "", currentDate = "",currentDescription = "";
        Float currentPrice=0.0F;
        alltr="NoTr \tDate      \tType\tCode \tQ    \tPrice\tDescription"+"\n-----\t----------\t---\t-----\t----\t-----\t------------";
        
        while (resultSet.next()) {
                currentNoTr = resultSet.getString(1);
                currentDate = resultSet.getString(2);
                currentType = resultSet.getString(3);
                currentCode = resultSet.getString(4);
                currentQ = resultSet.getString(5);
                currentPrice=Float.parseFloat(resultSet.getString(6));
                currentDescription = resultSet.getString(7);
                
                alltr=alltr+"\n"+currentNoTr+"\t"+currentDate+"\t"+currentType+"\t"+currentCode+"\t"+currentQ+"\t"+currentPrice+"\t"+currentDescription;
            }
    
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
    
    return alltr;
    }

public static boolean ifExist(String newCode){
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
            
            String query="SELECT CODE FROM TRANSACTIONS WHERE CODE='"+newCode+"'";
            //System.out.println(query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                currentCode = resultSet.getString(1);
                if (currentCode.equals(newCode)){
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


   public static int SumQCodeType(String newCode, String newType){
    
    String url = "jdbc:derby://localhost:1527/InventoryDB;create=true";
    String driver = "org.apache.derby.jdbc.ClientDriver";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int sum=0;
    
    try {
        Class driverClass = Class.forName(driver);
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();
            
        resultSet = statement.executeQuery("SELECT * FROM TRANSACTIONS where CODE='"+newCode+"' AND TYPE='"+newType+"'");
        String currentType="",currentCode = ""; 
        String  currentDate = "",currentDescription = "";
        Float currentPrice=0.0F;
        int currentNoTr=0,currentQ = 0;
        
        while (resultSet.next()) {
                currentNoTr = Integer.parseInt(resultSet.getString(1)); //System.out.print("\n"+currentNoTr);
                currentDate = resultSet.getString(2); //System.out.print("\t"+currentDate);
                currentType = resultSet.getString(3); //System.out.print("\t"+currentType);
                currentCode = resultSet.getString(4); //System.out.print("\t"+currentCode);
                currentQ = Integer.parseInt(resultSet.getString(5));  //System.out.print("\t"+currentQ);
                currentPrice=Float.parseFloat(resultSet.getString(6));  //System.out.print("\t"+currentPrice);
                currentDescription = resultSet.getString(7);  //System.out.print("\t"+currentDescription);
                sum+=currentQ;
             }
    
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
    
    
    return sum;
    }




}
