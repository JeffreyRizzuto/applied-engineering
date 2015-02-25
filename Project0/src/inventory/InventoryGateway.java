package inventory;

import java.util.HashMap;

public interface InventoryGateway {
	public void addPart(Part part);
	public HashMap<Integer, Item> getItems();
	public HashMap<Integer, Part> getParts();
	public void loadParts();
	public void loadItems();
	public void addItem(Item item);
	public void removeItem(int id);
	public void removePart(int id);
	public void close();
}
