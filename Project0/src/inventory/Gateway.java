package inventory;

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
	public void addProduct(Product product);
	public boolean removeItem(int id);
	public boolean removePart(int id);
	public boolean removeProduct(int id);
	public boolean checkLock(int id);
	public void lockPart(int id);
	public void unlockPart(int id);
	public boolean checkPartAssociation(int id);
	public HashMap getAssociatedParts(int productId);
	public void close();
}
