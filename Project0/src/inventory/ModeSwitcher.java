package inventory;

public class ModeSwitcher {
	InventoryView iView;
	ProductView pView;	
	
	
	public void switchToInv() {
		pView.setVisible(false);
		iView.setVisible(true);
	}
	
	public void switchToProd() {
		iView.setVisible(false);
		pView.setVisible(true);
	}
	
	public void registerInv(InventoryView view) {
		this.iView = view;
	}
	
	public void registerProd(ProductView view) {
		this.pView = view;
	}
}
