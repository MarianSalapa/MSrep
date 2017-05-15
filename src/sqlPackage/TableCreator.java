package sqlPackage;

/**
 * @author MMM
 */
public class TableCreator {
    
    public static void main(String[] args) {
        
        String query = "CREATE TABLE ITEMS ("
                               + "code VARCHAR(5) PRIMARY KEY, "
                               + "name VARCHAR(30), "
                               + "Q INTEGER, "
                               + "um VARCHAR(2), "
                               + "Price FLOAT(2) "
                               + ")"; 
        if(QueriesSQL.SimpleQuery(query,false)){
            System.out.println("Table ITEMS crated");
        }   
            query = "CREATE TABLE TRANSACTIONS"
                        + " (NoTR INTEGER PRIMARY KEY,"
                        + "DATETR VARCHAR(10),"
                        + "TYPE VARCHAR(3),"
                        + "code VARCHAR(5),"
                        + "Q INTEGER,Price FLOAT(2),"
                        + "Description VARCHAR(30),"
                        + " FOREIGN KEY (code) REFERENCES ITEMS(code)"
                        + ")"; 
        if(QueriesSQL.SimpleQuery(query,false)){
            System.out.println("Table TRANSACTIONS crated");
            
        }
                        
    }
}
