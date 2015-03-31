package inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InventoryMenuController extends KeyAdapter implements
		ActionListener {
	private InventoryModel model;
	private InventoryView view;
	private Session session;

	public InventoryMenuController(InventoryModel model, InventoryView view) {
		this.model = model;
		this.view = view;
		session = model.getLoggedInUser();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Exit")) {
			view.dispose();
			System.exit(0);
		}
		if (command.equals("Inventory View")) {
			return;// do nothing, we are in this mode
		}
		if (command.equals("Product View")) {
			if (session.canViewProductTemplates) {
				view.changeMode();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
			}
		}
		if (command.equals("parts")) {
			if (session.canViewParts) {
				view.changeView(0);
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
			}
		}
		if (command.equals("items")) {
			if (session.canViewInventory) {
				view.changeView(1);
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Access Denied");
			}
		}
	}
}
