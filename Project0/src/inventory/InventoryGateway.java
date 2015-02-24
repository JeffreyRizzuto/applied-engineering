package inventory;

import java.util.HashMap;

public interface InventoryGateway {
	public void addPart(Part part);
	public HashMap<?, ?> getItems();
	public HashMap<?, ?> getParts();
	public void addItem(Item item);
	public void removeItem();
	public void removePart();
	public void close();
}
