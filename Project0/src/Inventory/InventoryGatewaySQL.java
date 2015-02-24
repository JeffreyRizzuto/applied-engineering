package inventory;

import inventory.GatewayException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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
	
	public void loadParts() {
    	 statement = null;
    	 result = null;
         try {
        	 statement = connection.prepareStatement("select * from PARTS");
        	 statement.setInt(1, invId);
        	 result = statement.executeQuery();
        	 result.first();
         } catch (SQLException e) {
             e.printStackTrace();
         }
 	}
	
	public void loadItems() {
   	 statement = null;
   	 result = null;
        try {
       	 statement = connection.prepareStatement("select * from Inventory");
       	 statement.setInt(1, invId);
       	 result = statement.executeQuery();
       	 result.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public HashMap<Integer, Part> getParts() {
		HashMap<Integer, Part> items = new HashMap<Integer, Part>();
		try {
			while(result.next()) {
				Part part = new Part();
				part.setId(result.getInt(1));
				part.setPartNumber(result.getString(2));
				part.setPartName(result.getString(3));
				part.setVendor(result.getString(4));
				part.setUnitType(result.getString(5));
				part.setExternalPartNumber(result.getString(6));
				
				items.put(new Integer(result.getInt(1)), part);
				
				return items;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<Integer, Item> getItems() {
		HashMap<Integer, Item> items = new HashMap<Integer, Item>();
		try {
			while(result.next()) {
				Item item = new Item();
				item.setId(result.getInt(1));
				item.setPart(result.getInt(2));
				item.setUnitLocation(result.getString(3));
				item.setQuantity(result.getInt(4));

				
				items.put(new Integer(result.getInt(1)), item);
				
				return items;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addPart(Part part) {
	  	 statement = null;
	   	 result = null;
	   	 String id = String.valueOf(part.getId());
	   	 String partNumber = part.getPartNumber();
	   	 String partName = part.getPartName();
	   	 String vendor = part.getVendor();
	   	 String unit = part.getUnitType();
	   	 String extPartNum = part.getExternalPartNumber();
	        try {
	       	 statement = connection.prepareStatement("INSERT INTO Parts "
	       	 		+ "VALUES ('" + id + "', '" + partNumber + "', '"
	       	 		+ partName + "', '" + vendor + "', '" + unit
	       	 		+ "', ' + extPartNum" + "')");
	       	 statement.setInt(1, invId);
	       	 result = statement.executeQuery();
	       	 result.first();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void addItem(Item item) {
	  	 statement = null;
	   	 result = null;
	   	 String id = String.valueOf(item.getId());
	   	 String itemSomething = String.valueOf(item.getPart());
	   	 String location = item.getLocation();
	   	 String quantity = String.valueOf(item.getQuantity());

	        try {
	       	 statement = connection.prepareStatement("INSERT INTO Inventory "
	       	 		+ "VALUES ('" + id + "', '" + itemSomething + "', '"
	       	 		+ location + "', '" + quantity + "')");
	       	 statement.setInt(1, invId);
	       	 result = statement.executeQuery();
	       	 result.first();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void removePart() {
		
	}	
     
	public void removeItem() {
		
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