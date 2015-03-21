package inventory;

public class ListObserver {
	InventoryView Iview;
	ProductView Pview;
	public ListObserver() {
		
	}

	public void setView(InventoryView view) {
		this.Iview = view;
	}
	public void update() {
		Pview.update();
		Iview.update();
	}

	public void setView(ProductView view) {
		this.Pview = view;
		
	}
}
