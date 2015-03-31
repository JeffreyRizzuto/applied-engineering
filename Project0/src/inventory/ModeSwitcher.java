package inventory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModeSwitcher {
	InventoryView iView;
	ProductView pView;
	Session session;

	public ModeSwitcher(Session session) {
		this.session = session;
	}

	public void switchToInv() {
		if (session.canViewInventory) {
			pView.setVisible(false);
			iView.setVisible(true);
		} else if (session.canViewParts) {
			iView.setVisible(true);
			pView.setVisible(false);
			iView.changeMode();
		}

	}

	public void switchToProd() {
		if (session.canViewProductTemplates) {
			iView.setVisible(false);
			pView.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
		}

	}

	public void registerInv(InventoryView view) {
		this.iView = view;
	}

	public void registerProd(ProductView view) {
		this.pView = view;
	}
}
