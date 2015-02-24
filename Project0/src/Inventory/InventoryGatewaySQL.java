package inventory;

import inventory.GatewayException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class InventoryGatewaySQL implements InventoryGateway{
	private static final boolean DEBUG = true;
	
	private Connection connection;
	PreparedStatement statement;
    private ResultSet result;
    private int invId;
    
	InventoryGatewaySQL(int invId, int isoLevel) throws GatewayException {
		this.invId = invId;
		
		//read the properties file to establish the db connection
		DataSource ds = getDataSource();
		if(ds == null) {
        	throw new GatewayException("Datasource is null!");
        }
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			throw new GatewayException("SQL Error: " + e.getMessage());
		}
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
	
	public void updateInventory() {
		
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
         	MysqlDataSource mysqlDS = new MysqlDataSource();
         	mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
         	mysqlDS.setUser(props.getProperty("MYSQL_DB_PONGUSER"));
         	mysqlDS.setPassword(props.getProperty("MYSQL_DB_PONGPW"));
         	return mysqlDS;
         } catch(RuntimeException e) {
             e.printStackTrace();
             return null;
         }
 	}

	@Override
	public void close() {
		if(DEBUG)
			System.out.println("Closing db connection...");
		try {
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}