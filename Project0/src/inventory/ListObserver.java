package inventory;

public class ListObserver {
	InventoryView view;
	public ListObserver() {
		
	}

	public void setView(InventoryView view) {
		this.view = view;
	}
	public void update() {
		view.update();
		
	}
}
