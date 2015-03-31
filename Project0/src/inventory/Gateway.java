package inventory;

import java.util.ArrayList;
import java.util.HashMap;

public interface Gateway {
	
	public HashMap<Integer, Item> getItems();
	public HashMap<Integer, Part> getParts();
	public HashMap<Integer, Product> getProducts();
	public void loadParts();
	public void loadItems();
	public void loadProducts();
	public void addPart(Part part);
	public void addItem(Item item);
	public Part getPart(String partNum);
	public void addProduct(Product product);
	public boolean removeItem(int id);
	public void addProductPart(Product product, Part part);
	public boolean removePart(int id);
	public boolean removeProduct(int id);
	public boolean checkLock(int id);
	public void removeProductPart(Product product, Part part);
	public void lockPart(int id);
	public void unlockPart(int id);
	public boolean checkPart(String partNum);
	public boolean checkPartAssociation(int id);
	public ArrayList<Part> getProductParts(int productId);
	public void close();
}
