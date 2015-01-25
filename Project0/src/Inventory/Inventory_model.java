package Inventory;

public class Inventory_model {
	
	private boolean item_open;
	public Inventory_model() {
			item_open = false;
	}
	
	public boolean getItem_open() {
		return item_open;
	}
	
	public void setItem_open(boolean item_open) {
		this.item_open = item_open;
	}

}
