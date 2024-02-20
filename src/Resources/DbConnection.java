package Resources;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private static Connection con=null;
    private DbConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/serviceHub", "root", "tplkmfcsip");
        }
        catch(Exception e){
            System.out.println("Error connecting to database");
        }
    }
    public static Connection getConnection(){
        if(con==null){
            new DbConnection();
        }
        return con;
    }
}
