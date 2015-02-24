package Inventory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

public class InventoryGatewaySQL {
	
	Connection connection = null;
    Statement statement = null;
    ResultSet result = null;
    
	private InventoryGatewaySQL(){
	     
	     String url = "jdbc:mysql://localhost:3306/testdb";
	     String user = "xhd629";
	     String password = "koQw2K2pRb7R5fJe9AaP​";
	}
	
     public void loadInventory() {
    	 statement = null;
    	 result = null;
         try {
        	 statement = connection.prepareStatement("select * from pong_game where id = ?");
        	 //statement.setInt(1, gameId);
        	// result = statement.executeQuery();
        	 result.first();
         } catch (SQLException e) {
             e.printStackTrace();
         }
 	}
     
     public DataSource getDataSource() {
 		Properties props = new Properties();
 		FileInputStream fis = null;
         try {
         	fis = new FileInputStream("db.properties");
             props.load(fis);
         } catch (IOException e) {
             e.printStackTrace();
             return null;
         }
         try {
         	//MysqlDataSource mysqlDS = new MysqlDataSource();
         	//mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
         	//mysqlDS.setUser(props.getProperty("MYSQL_DB_PONGUSER"));
         	//mysqlDS.setPassword(props.getProperty("MYSQL_DB_PONGPW"));
         	//return mysqlDS;
         } catch(RuntimeException e) {
             e.printStackTrace();
             return null;
         }
         /* remove this */
		return null;
 	}
}