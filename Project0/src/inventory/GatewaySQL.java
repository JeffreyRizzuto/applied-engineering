package inventory;

import inventory.GatewayException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class GatewaySQL implements Gateway {
	private static final boolean DEBUG = true;

	private Connection connection;
	PreparedStatement statement;
	private ResultSet result;
	private int invId;

	GatewaySQL(int invId, int isoLevel) throws GatewayException {
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
			statement = connection.prepareStatement("select * from parts");
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

	public void loadProducts() {
		statement = null;
		result = null;
		try {
			statement = connection.prepareStatement("select * from products");
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

				// add that new item to the list
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

	public HashMap<Integer, Product> getProducts() {
		HashMap<Integer, Product> products = new HashMap<Integer, Product>();
		try {
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt(1));
				product.setNumber(result.getString(2));
				product.setDescription(result.getString(3));
				products.put(new Integer(result.getInt(1)), product);
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
		return products;
	}

	public void addPart(Part part) {
		statement = null;
		result = null;
		int partId = 0;
		String partNumber = part.getPartNumber();
		String partName = part.getPartName();
		String vendor = part.getVendor();
		String unit = part.getUnitType();
		String extPartNum = part.getExternalPartNumber();
		try {
			statement = connection
					.prepareStatement("INSERT INTO parts (part_number,name,vendor,unit,external_part_number) "
							+ "VALUES ('"
							+ partNumber
							+ "', '"
							+ partName
							+ "', '"
							+ vendor
							+ "', '"
							+ unit
							+ "', '"
							+ extPartNum + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// cool little function built into mysql to get the id just generated,
		// add it to the part
		try {
			statement = connection
					.prepareStatement("SELECT LAST_INSERT_ID() AS id from parts");
			result = statement.executeQuery();
			result.next();
			partId = result.getInt("id");
			part.setId(partId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// now add the lock (if there is a way to link to tables in mysql, i
		// don't know it :( )
		try {
			statement = connection
					.prepareStatement("INSERT INTO partLock VALUES " + "('"
							+ partId + "', '" + 0 + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addItem(Item item) {
		statement = null;
		result = null;
		int partId = 0;
		int part = item.getPart();
		String unitLocation = item.getUnitLocation();
		int quantity = item.getQuantity();
		try {
			statement = connection
					.prepareStatement("INSERT INTO Inventory (part,location,quantity) "
							+ "VALUES ('"
							+ part
							+ "', '"
							+ unitLocation
							+ "', '" + quantity + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// cool little function built into mysql to get the id just generated,
		// add it to the part
		try {
			statement = connection
					.prepareStatement("SELECT LAST_INSERT_ID() AS id from Inventory");
			result = statement.executeQuery();
			result.next();
			partId = result.getInt("id");
			item.setId(partId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addProduct(Product product) {
		statement = null;
		result = null;
		int id;
		String number = product.getNumber();
		String description = product.getDescription();

		try {
			statement = connection
					.prepareStatement("INSERT INTO products (number,description) "
							+ "VALUES ('"
							+ number
							+ "', '"
							+ description
							+ "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// get auto generated id
		try {
			statement = connection
					.prepareStatement("SELECT LAST_INSERT_ID() AS id from products");
			result = statement.executeQuery();
			result.next();
			id = result.getInt("id");
			product.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addProductPart(Product product, Part part) {
		statement = null;
		result = null;
		int productId = product.getId();
		int partId = part.getId();
		try {
			statement = connection
					.prepareStatement("INSERT INTO productParts (product,part) "
							+ "VALUES ('" + productId + "', '" + partId + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeProductPart(Product product, Part part) {
		statement = null;
		result = null;
		int productId = product.getId();
		int partId = part.getId();
		try {
			statement = connection
					.prepareStatement("DELETE FROM productParts WHERE product = "+ productId +" AND part = " + partId + " Limit 1");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkPart(String partNum) {
		statement = null;
		result = null;
		// remove the part
		try {
			statement = connection
					.prepareStatement("SELECT * FROM `parts` WHERE part_number = '" + partNum +"'");
			result = statement.executeQuery();
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public Part getPart(String partNum) {
		statement = null;
		result = null;
		// remove the part
		try {
			statement = connection
					.prepareStatement("SELECT * FROM `parts` WHERE part_number = '" + partNum +"'");
			result = statement.executeQuery();
			if(result.next()) {
				Part part = new Part();
				part.setId(result.getInt(1));
				part.setPartNumber(result.getString(2));
				part.setPartName(result.getString(3));
				part.setVendor(result.getString(4));
				part.setUnitType(result.getString(5));
				part.setExternalPartNumber(result.getString(6));
				return part;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean removePart(int id) {
		statement = null;
		result = null;
		if (checkPartAssociation(id)) {
			return false;
		}
		// remove the part
		try {
			statement = connection
					.prepareStatement("DELETE FROM parts WHERE id = " + id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		// remove the lock
		try {
			statement = connection
					.prepareStatement("DELETE FROM partLock WHERE id = " + id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeItem(int id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM Inventory WHERE id=" + id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean removeProduct(int id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM products WHERE id=" + id);
			statement.executeUpdate();
			
			statement = connection.prepareStatement("DELETE FROM productParts WHERE product = "+ id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

	// stuff to handle the lock
	public boolean checkLock(int Id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM partLock WHERE id = '"
							+ Id + "'");
			result = statement.executeQuery();
			result.next();
			return (result.getBoolean("open"));
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
			statement = connection
					.prepareStatement("UPDATE partLock SET open = 1 WHERE id = "
							+ Id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void unlockPart(int Id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("UPDATE partLock SET open = 0 WHERE id = "
							+ Id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkPartAssociation(int Id) {
		statement = null;
		result = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM productParts WHERE part = '"
							+ Id + "'");
			result = statement.executeQuery();

			return (result.next());// if there was a part associated to a
									// product, return true
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Part> getProductParts(int productId) {
		ArrayList<Part> parts = new ArrayList<Part>();
		statement = null;
		result = null;
		ResultSet matchingParts = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM productParts WHERE product = '"
							+ productId + "'");
			matchingParts = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (matchingParts.next()) {
				statement = connection
						.prepareStatement("SELECT * FROM parts WHERE id = '"
								+ matchingParts.getInt("part") + "'");
				result = statement.executeQuery();
				while (result.next()) {// should only be one result
					Part part = new Part();
					part.setId(result.getInt(1));
					part.setPartNumber(result.getString(2));
					part.setPartName(result.getString(3));
					part.setVendor(result.getString(4));
					part.setUnitType(result.getString(5));
					part.setExternalPartNumber(result.getString(6));
					parts.add(part);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parts;
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