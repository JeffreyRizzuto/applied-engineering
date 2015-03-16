package inventory;

import inventory.GatewayException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class InventoryGatewaySQL implements InventoryGateway {
	private static final boolean DEBUG = true;

	private Connection connection;
	PreparedStatement statement;
	private ResultSet result;
	private int invId;

	InventoryGatewaySQL(int invId, int isoLevel) throws GatewayException {
		this.invId = invId;

		// read the properties file to establish the db connection
		DataSource ds = getDataSource();
		if (ds == null) {
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
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void loadItems() {
		statement = null;
		result = null;
		try {
			statement = connection.prepareStatement("select * from Inventory");
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Part> getParts() {
		HashMap<Integer, Part> items = new HashMap<Integer, Part>();
		try {
			while (result.next()) {
				Part part = new Part();
				part.setId(result.getInt(1));
				part.setPartNumber(result.getString(2));
				part.setPartName(result.getString(3));
				part.setVendor(result.getString(4));
				part.setUnitType(result.getString(5));
				part.setExternalPartNumber(result.getString(6));
				items.put(new Integer(part.getId()), part);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public HashMap<Integer, Item> getItems() {
		HashMap<Integer, Item> items = new HashMap<Integer, Item>();
		try {
			while (result.next()) {
				Item item = new Item();
				item.setId(result.getInt(1));
				item.setPart(result.getInt(2));
				item.setUnitLocation(result.getString(3));
				item.setQuantity(result.getInt(4));
				items.put(new Integer(result.getInt(1)), item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public void addPart(Part part) {
		statement = null;
		result = null;
		int id = part.getId();
		String partNumber = part.getPartNumber();
		String partName = part.getPartName();
		String vendor = part.getVendor();
		String unit = part.getUnitType();
		String extPartNum = part.getExternalPartNumber();
		try {
			statement = connection.prepareStatement("INSERT INTO PARTS "
					+ "VALUES ('" + id + "', '" + partNumber + "', '"
					+ partName + "', '" + vendor + "', '" + unit + "', ' "
					+ extPartNum + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//now add the lock (if there is a way to link to tables in mysql, i don't know it :( )
		try {
			statement = connection.prepareStatement("INSERT INTO partLock VALUES" + part.getId() + 0);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addItem(Item item) {
		statement = null;
		result = null;
		int id = item.getId();
		int part = item.getPart();
		String location = item.getLocation();
		int quantity = item.getQuantity();

		try {
			statement = connection.prepareStatement("INSERT INTO Inventory "
					+ "VALUES ('" + id + "', '" + part + "', '" + location
					+ "', '" + quantity + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removePart(int id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM PARTS WHERE part_id=" + id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//remove the lock
		try {
			statement = connection
					.prepareStatement("DELETE FROM partLock WHERE Id=" + id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeItem(int id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM Inventory WHERE id=" + id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// stuff to handle the lock
	public boolean checkLock(int Id) {
		statement = null;
		result = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM partLock Where Id=" + Id);
			result = statement.executeQuery();
			result.next();
			return(result.getBoolean("Lock"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// stuff to handle the lock
	public void lockPart(int Id) {
		statement = null;
		result = null;
		try {
			statement = connection.prepareStatement("UPDATE partLock SET Lock=1 Where Id=" + Id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void unlockPart(int Id) {
		statement = null;
		result = null;
		try {
			statement = connection.prepareStatement("UPDATE partLock SET Lock=0 Where Id=" + Id);
			statement.executeUpdate();
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
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_INVUSER"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_INVPW"));
			return mysqlDS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void close() {
		if (DEBUG)
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